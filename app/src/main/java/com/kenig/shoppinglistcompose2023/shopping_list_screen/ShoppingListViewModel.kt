package com.kenig.shoppinglistcompose2023.shopping_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.data.ShoppingListItem
import com.kenig.shoppinglistcompose2023.data.ShoppingListRepository
import com.kenig.shoppinglistcompose2023.dialog.DialogController
import com.kenig.shoppinglistcompose2023.dialog.DialogEvent
import com.kenig.shoppinglistcompose2023.utils.UiEvent
import com.kenig.shoppinglistcompose2023.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel(), DialogController {
    var listItem: ShoppingListItem? = null
    val list = repository.getAllItems()

    private val _uiEvent = Channel<UiEvent>() //передатчик класса UiEvent() через Channel
    val uiEvent = _uiEvent.receiveAsFlow() //приёмник UiEvent

    override var dialogTitle = mutableStateOf("")
        private set //могу записать/изменить только в этом классе, а считать (get) в любом
    override var editableText = mutableStateOf("")
        private set //могу записать/изменить только в этом классе, а считать (get) в любом
    override var openDialog = mutableStateOf(false)
        private set //могу записать/изменить только в этом классе, а считать (get) в любом
    override var showEditableText = mutableStateOf(false)
        private set //могу записать/изменить только в этом классе, а считать (get) в любом

    override fun onDialogEvent(event: DialogEvent) { //события Диалога на экране ShoppingListScreen
        when (event) {
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            is DialogEvent.OnConfirmButton -> {
                if (showEditableText.value) {
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        listItem?.let { repository.deleteItem(it) }
                    }
                }
                openDialog.value = false
            }
            is DialogEvent.OnCancelButton -> {
                openDialog.value = false
            }
        }
    }

    fun onEvent(event: ShoppingListEvent) { //события на экране ShoppingListScreen
        when (event) {
            is ShoppingListEvent.OnShowDeleteDialog -> {//при нажатии кнопки DeleteButton
                listItem = event.item
                openDialog.value = true
                showEditableText.value = false
                dialogTitle.value = "Delete this item?"
            }
            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item
                openDialog.value = true
                showEditableText.value = true
                dialogTitle.value = "List name: "
                editableText.value = listItem?.name ?: ""
            }
            is ShoppingListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is ShoppingListEvent.OnItemSave -> {
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            listItem?.id,
                            editableText.value,
                            getCurrentTime(),
                            listItem?.allItemsCount ?: 0,
                            listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) { //функция для передачи UiEvent() на экран ShopLisScr
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}