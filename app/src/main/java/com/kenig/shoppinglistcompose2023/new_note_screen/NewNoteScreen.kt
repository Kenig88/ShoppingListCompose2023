package com.kenig.shoppinglistcompose2023.new_note_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NewNoteScreen() {
    Text(
        text = "New note",
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
}