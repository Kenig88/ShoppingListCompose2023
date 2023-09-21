package com.kenig.shoppinglistcompose2023.add_item_screen

import com.kenig.shoppinglistcompose2023.data.AddItem

sealed class AddItemEvent {
    data class OnDelete(val item: AddItem) : AddItemEvent()
    object OnItemSave : AddItemEvent()
    data class OnShowEditDialog(val item: AddItem) : AddItemEvent()
    data class OnTextChange(val text: String) : AddItemEvent()
    data class OnCheckedChange(val item: AddItem) : AddItemEvent()
}