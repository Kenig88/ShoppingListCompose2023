package com.kenig.shoppinglistcompose2023.shopping_list_screen

import com.kenig.shoppinglistcompose2023.data.ShoppingListItem

sealed class ShoppingListEvent {
    data class OnShowDeleteDialog(val item: ShoppingListItem) : ShoppingListEvent()
    data class OnShowEditDialog(val item: ShoppingListItem) : ShoppingListEvent()
    data class OnItemClick(val route: String) : ShoppingListEvent()
    object OnItemSave : ShoppingListEvent()
}