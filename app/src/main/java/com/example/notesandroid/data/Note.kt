package com.example.notesandroid.data

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val category: String = "Umum",
    val createdAt: Long = System.currentTimeMillis()
)
