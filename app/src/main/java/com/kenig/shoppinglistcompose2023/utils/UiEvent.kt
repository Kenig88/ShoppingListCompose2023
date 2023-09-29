package com.kenig.shoppinglistcompose2023.utils

sealed class UiEvent {
    object PopBackStack : UiEvent() //вернуться назад
    data class Navigate(val route: String) : UiEvent() //перейти на другой экран
    data class MainNavigate(val route: String) : UiEvent()
    data class ShowSnackBar(val message: String) : UiEvent()
}