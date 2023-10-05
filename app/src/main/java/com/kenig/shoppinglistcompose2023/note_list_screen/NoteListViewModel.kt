package com.kenig.shoppinglistcompose2023.note_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.data.NoteItem
import com.kenig.shoppinglistcompose2023.data.NoteRepository
import com.kenig.shoppinglistcompose2023.datastore.DataStoreManager
import com.kenig.shoppinglistcompose2023.dialog.DialogController
import com.kenig.shoppinglistcompose2023.dialog.DialogEvent
import com.kenig.shoppinglistcompose2023.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository,
    dataStoreManager: DataStoreManager
) : ViewModel(), DialogController {

    val noteListFlow = repository.getAllItems()
    private var noteItem: NoteItem? = null

    var noteList by mutableStateOf(listOf<NoteItem>())
    var originNoteList = listOf<NoteItem>()

    private val _uiEvent = Channel<UiEvent>() //передатчик класса UiEvent() через Channel
    val uiEvent = _uiEvent.receiveAsFlow() //приёмник UiEvent

    var titleColor = mutableStateOf("#FF000000")

    var searchText by mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Delete this note?") /////изменить в ресурсах!!!
        private set //могу записать/изменить только в этом классе, а считать (get) в любом
    override var editableText = mutableStateOf("")
        private set //могу записать/изменить только в этом классе, а считать (get) в любом
    override var openDialog = mutableStateOf(false)
        private set //могу записать/изменить только в этом классе, а считать (get) в любом
    override var showEditableText = mutableStateOf(false)
        private set //могу записать/изменить только в этом классе, а считать (get) в любом

    init {
        viewModelScope.launch {
            dataStoreManager.getStringPreference(
                DataStoreManager.TITLE_COLOR,
                "#FF000000"
            ).collect { color ->
                titleColor.value = color
            }
        }
        viewModelScope.launch {
            noteListFlow.collect { list ->
                noteList = list
                originNoteList = list
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) { //события Диалога на экране ShoppingListScreen
        when (event) {
            is DialogEvent.OnConfirmButton -> {
                viewModelScope.launch {
                    repository.deleteItem(noteItem ?: return@launch)
                    sendUiEvent(UiEvent.ShowSnackBar("Undone delete item?"))
                }
                openDialog.value = false
            }
            is DialogEvent.OnCancelButton -> {
                openDialog.value = false
            }
            else -> {}
        }
    }

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is NoteListEvent.OnShowDeleteDialog -> { //при вызове диалога отправляю noteItem в глобальную переменную noteItem (сверху)
                openDialog.value = true
                noteItem = event.item
            }
            is NoteListEvent.UndoneDeleteItem -> {
                viewModelScope.launch {
                    noteItem?.let { repository.insertItem(it) }
                }
            }
            is NoteListEvent.OnTextSearchChange -> {
                searchText = event.text
                noteList = originNoteList.filter { note ->
                    note.title.lowercase().startsWith(searchText.lowercase())
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) { // это чтобы отправлять на экран события класса UiEvent
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}