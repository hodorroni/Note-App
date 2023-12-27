package eu.tutorials.noteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.noteapp.data.NotesDataSource
import eu.tutorials.noteapp.model.Note
import eu.tutorials.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository):ViewModel() {
    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    //public variable
    val noteList = _noteList.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{
                    listOfNotes ->
                    _noteList.value = listOfNotes
                }
        }
        //noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note:Note) = viewModelScope.launch {
        repository.addNote(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }
  fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}