package com.example.notesandroid.data

import android.content.ContentValues
import android.database.Cursor

class NoteRepositoryImpl(private val dbHelper: NoteDbHelper) {

    fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            NoteDbHelper.TABLE_NOTES,
            null,
            null,
            null,
            null,
            null,
            "${NoteDbHelper.COLUMN_CREATED_AT} DESC"
        )

        cursor.use { c ->
            val idIndex = c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_ID)
            val titleIndex = c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_TITLE)
            val contentIndex = c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_CONTENT)
            val categoryIndex = c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_CATEGORY)
            val createdAtIndex = c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_CREATED_AT)

            while (c.moveToNext()) {
                notes.add(
                    Note(
                        id = c.getLong(idIndex),
                        title = c.getString(titleIndex),
                        content = c.getString(contentIndex),
                        category = c.getString(categoryIndex),
                        createdAt = c.getLong(createdAtIndex)
                    )
                )
            }
        }
        return notes
    }

    fun insertNote(note: Note): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NoteDbHelper.COLUMN_TITLE, note.title)
            put(NoteDbHelper.COLUMN_CONTENT, note.content)
            put(NoteDbHelper.COLUMN_CATEGORY, note.category)
            put(NoteDbHelper.COLUMN_CREATED_AT, note.createdAt)
        }
        return db.insert(NoteDbHelper.TABLE_NOTES, null, values)
    }

    fun deleteNote(id: Long): Int {
        val db = dbHelper.writableDatabase
        return db.delete(
            NoteDbHelper.TABLE_NOTES,
            "${NoteDbHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
    }
}
