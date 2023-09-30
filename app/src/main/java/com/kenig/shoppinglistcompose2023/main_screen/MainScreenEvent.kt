package com.kenig.shoppinglistcompose2023.main_screen

sealed class MainScreenEvent {
    object OnShowEditDialog : MainScreenEvent()
    object OnItemSave : MainScreenEvent()
    data class Navigate(val route: String) : MainScreenEvent()
    data class MainNavigate(val route: String) : MainScreenEvent()
}