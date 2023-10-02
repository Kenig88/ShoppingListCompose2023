package com.kenig.shoppinglistcompose2023.new_note_screen

sealed class NewNoteEvent {
    data class OnTitleChange(val text: String) : NewNoteEvent()
    data class OnDescriptionChange(val text: String) : NewNoteEvent()
    object OnSave : NewNoteEvent()
}
