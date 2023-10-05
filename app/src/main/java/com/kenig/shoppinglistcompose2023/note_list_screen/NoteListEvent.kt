package com.kenig.shoppinglistcompose2023.note_list_screen

import com.kenig.shoppinglistcompose2023.data.NoteItem

sealed class NoteListEvent {
    data class OnShowDeleteDialog(val item: NoteItem) : NoteListEvent()
    data class OnItemClick(val route: String) : NoteListEvent()
    object UndoneDeleteItem : NoteListEvent()
    data class OnTextSearchChange(val text: String) : NoteListEvent()
}
