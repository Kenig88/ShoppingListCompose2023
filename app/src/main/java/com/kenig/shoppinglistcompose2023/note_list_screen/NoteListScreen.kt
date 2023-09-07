package com.kenig.shoppinglistcompose2023.note_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteListScreen() {
    Text(
        text = "Note list",
        modifier = Modifier.fillMaxSize().
        wrapContentWidth().
        wrapContentHeight()
    )
}