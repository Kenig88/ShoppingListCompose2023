package com.kenig.shoppinglistcompose2023.shopping_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.data.ShoppingListItem
import com.kenig.shoppinglistcompose2023.data.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {
    private var listItem: ShoppingListItem? = null

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
            }
            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item
            }
            is ShoppingListEvent.OnItemClick -> {

            }
            is ShoppingListEvent.OnItemSave -> {
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            listItem?.id,
                            "list 1",
                            "09.09.2023.13:54",
                            listItem?.allItemsCount ?: 0,
                            listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
        }
    }
}