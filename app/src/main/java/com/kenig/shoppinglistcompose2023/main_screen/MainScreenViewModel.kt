package com.kenig.shoppinglistcompose2023.main_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.data.ShoppingListItem
import com.kenig.shoppinglistcompose2023.data.ShoppingListRepository
import com.kenig.shoppinglistcompose2023.dialog.DialogController
import com.kenig.shoppinglistcompose2023.dialog.DialogEvent
import com.kenig.shoppinglistcompose2023.utils.Routes
import com.kenig.shoppinglistcompose2023.utils.UiEvent
import com.kenig.shoppinglistcompose2023.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val repository: ShoppingListRepository
) : ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>() //передатчик класса UiEvent() через Channel
    val uiEvent = _uiEvent.receiveAsFlow() //приёмник UiEvent

    var showFloatingButton = mutableStateOf(true)
        private set

    override var dialogTitle = mutableStateOf("List name: ") /////изменить в ресурсах!!!
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnConfirmButton -> {
                onEvent(MainScreenEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnCancelButton -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnNewItemClick -> {
                if (event.route == Routes.SHOPPING_LIST) {
                    openDialog.value = true
                } else {
                    sendUiEvent(UiEvent.MainNavigate(Routes.NEW_NOTE + "/-1"))
                }
            }
            is MainScreenEvent.OnItemSave -> {
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            null,
                            editableText.value,
                            getCurrentTime(),
                            0,
                            0
                        )
                    )
                }
            }
            is MainScreenEvent.Navigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))
                showFloatingButton.value =
                    if (event.route == Routes.SETTINGS) { // чтобы не показывать кнопку при экране Settings
                        false // не показывает кнопку
                    } else {
                        true //показывает на других экранах
                    }
            }
            is MainScreenEvent.MainNavigate -> {
                sendUiEvent(UiEvent.MainNavigate(event.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) { // это чтобы отправлять на экран события класса UiEvent
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}