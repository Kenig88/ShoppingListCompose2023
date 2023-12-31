package com.kenig.shoppinglistcompose2023.new_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.data.NoteItem
import com.kenig.shoppinglistcompose2023.data.NoteRepository
import com.kenig.shoppinglistcompose2023.datastore.DataStoreManager
import com.kenig.shoppinglistcompose2023.utils.UiEvent
import com.kenig.shoppinglistcompose2023.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle,
    dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>() //передатчик класса UiEvent() через Channel
    val uiEvent = _uiEvent.receiveAsFlow() //приёмник UiEvent

    private var noteId = -1
    private var noteItem: NoteItem? = null // если равна Null то создаю новый элемент, если НЕ равна то редактирую уже созданный элемент

    var titleColor = mutableStateOf("#FF000000")

    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt() ?: -1
        if (noteId != -1) { //если НЕ равен -1 то заметка считывается
            viewModelScope.launch {
                repository.getNoteItemById(noteId).let { noteItem ->
                    title = noteItem.title
                    description = noteItem.description
                    this@NewNoteViewModel.noteItem = noteItem
                }
                dataStoreManager.getStringPreference(
                    DataStoreManager.TITLE_COLOR,
                    "#FF000000"
                ).collect { color ->
                    titleColor.value = color
                }
            }
        }
    }

    fun onEvent(event: NewNoteEvent) {
        when (event) {
            is NewNoteEvent.OnTitleChange -> {
                title = event.text
            }
            is NewNoteEvent.OnDescriptionChange -> {
                description = event.text
            }
            is NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    if (title.isEmpty()) { //если заголовок будет пустой то ничего не сохранится
                        sendUiEvent(UiEvent.ShowSnackBar("Title can not be empty!"))
                        return@launch
                    }
                    repository.insertItem(
                        NoteItem(
                            noteItem?.id,
                            title,
                            description,
                            getCurrentTime()
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}