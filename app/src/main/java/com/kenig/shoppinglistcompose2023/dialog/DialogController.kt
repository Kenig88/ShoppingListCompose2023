package com.kenig.shoppinglistcompose2023.dialog

import androidx.compose.runtime.MutableState

interface DialogController {
    val dialogTitle: MutableState<String>
    val editableText: MutableState<String> //это когда ввожу текст он сразу будет меняться на экране
    val openDialog: MutableState<Boolean>
    val showEditableText: MutableState<Boolean> //показать или нет TextField диалога
    fun onDialogEvent(event: DialogEvent)
}