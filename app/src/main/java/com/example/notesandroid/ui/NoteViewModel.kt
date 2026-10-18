package com.example.notesandroid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesandroid.data.Note
import com.example.notesandroid.data.NoteRepositoryImpl

class NoteViewModel(private val repository: NoteRepositoryImpl) : ViewModel() {

    var notesState by mutableStateOf<List<Note>>(emptyList())
        private set

    var titleInput by mutableStateOf("")
    var contentInput by mutableStateOf("")
    var selectedCategory by mutableStateOf("Umum")

    init {
        loadNotes()
    }

    fun loadNotes() {
        notesState = repository.getAllNotes()
    }

    fun addNote() {
        if (titleInput.isBlank() || contentInput.isBlank()) return
        val newNote = Note(
            title = titleInput.trim(),
            content = contentInput.trim(),
            category = selectedCategory
        )
        repository.insertNote(newNote)
        titleInput = ""
        contentInput = ""
        loadNotes()
    }

    fun deleteNote(id: Long) {
        repository.deleteNote(id)
        loadNotes()
    }
}

class NoteViewModelFactory(private val repository: NoteRepositoryImpl) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
