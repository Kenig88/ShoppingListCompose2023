package com.kenig.shoppinglistcompose2023.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.data.AddItem
import com.kenig.shoppinglistcompose2023.data.AddItemRepository
import com.kenig.shoppinglistcompose2023.data.ShoppingListItem
import com.kenig.shoppinglistcompose2023.dialog.DialogController
import com.kenig.shoppinglistcompose2023.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle //для того чтобы получить доступ к аргументу "listId" при открытии экрана
) : ViewModel(), DialogController {

    var itemsList: Flow<List<AddItem>>? = null //получаю список когда в init уже есть идентификатор
    var addItem: AddItem? = null
    var listId: Int = -1 // чтобы listId не был Null
    var shoppingListItem: ShoppingListItem? = null

    init { //запускается когда инициализируется класс AddItemViewModel
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemsList = repository.getAllItemsById(listId)
        viewModelScope.launch {
            shoppingListItem = repository.getListItemById(listId)
        }
    }

    var itemText = mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Edit name: ")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            is DialogEvent.OnCancelButton -> {
                openDialog.value = false
                editableText.value = ""

            }
            is DialogEvent.OnConfirmButton -> {
                addItem = addItem?.copy(name = editableText.value)
                onEvent(AddItemEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }
        }
    }

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }
                updateShoppingListCount()
            }
            is AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch
                    repository.insertItem(
                        AddItem(
                            addItem?.id,
                            addItem?.name ?: itemText.value,
                            addItem?.isCheck ?: false,
                            listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
                updateShoppingListCount()
            }
            is AddItemEvent.OnShowEditDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""
            }
            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }
            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
                updateShoppingListCount()
            }
        }
    }

    private fun updateShoppingListCount() {
        viewModelScope.launch {
            itemsList?.collect { list -> //у Flow есть Collect, беру его так
                var counter = 0
                list.forEach { item ->
                    if (item.isCheck) counter++
                }
                shoppingListItem?.copy(
                    allItemsCount = list.size,
                    allSelectedItemsCount = counter
                )?.let { shopItem ->
                    repository.insertItem(
                        shopItem
                    )
                }
            }
        }
    }
}