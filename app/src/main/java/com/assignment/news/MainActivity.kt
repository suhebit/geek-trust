package com.assignment.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.assignment.news.ui.MainScreen
import com.assignment.news.ui.theme.NewsReaderTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the app.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsReaderTheme {
                MainScreen()
            }
        }
    }
}
