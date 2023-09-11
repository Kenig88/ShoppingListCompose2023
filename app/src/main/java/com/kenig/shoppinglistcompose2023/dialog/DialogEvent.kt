package com.kenig.shoppinglistcompose2023.dialog

sealed class DialogEvent {
    data class OnTextChange(val text: String) : DialogEvent()
    object OnCancelButton : DialogEvent()
    object OnConfirmButton : DialogEvent()
}
