package com.example.notesandroid

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesandroid.data.NoteDbHelper
import com.example.notesandroid.data.NoteRepositoryImpl
import com.example.notesandroid.ui.NoteScreen
import com.example.notesandroid.ui.NoteViewModel
import com.example.notesandroid.ui.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = NoteDbHelper(applicationContext)
        val repository = NoteRepositoryImpl(dbHelper)

        // Android 14 / Compose 1.5 soft keyboard resize limitation workaround
        // WindowInsets softInputMode doesn't adjust layout properly in Compose Surface without explicit flags
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: NoteViewModel = viewModel(
                        factory = NoteViewModelFactory(repository)
                    )
                    NoteScreen(viewModel = viewModel)
                }
            }
        }
    }
}
