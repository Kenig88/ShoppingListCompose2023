package com.kenig.shoppinglistcompose2023.utils

import com.kenig.shoppinglistcompose2023.data.ShoppingListItem

sealed class UiEvent {
    object PopBackStack : UiEvent() //вернуться назад
    data class Navigate(val route: String) : UiEvent() //перейти на другой экран
    data class ShowSnackBar(val title: String) : UiEvent()
}