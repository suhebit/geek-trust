package com.assignment.news.ui.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.news.ui.news_feed.ArticleCard

/**
 * The screen that displays the bookmarked articles.
 *
 * @param viewModel The view model for the screen.
 * @param onArticleClick A callback to be invoked when an article is clicked.
 */
@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    onArticleClick: (com.assignment.news.domain.model.Article) -> Unit
) {
    val bookmarks by viewModel.bookmarks.collectAsState()

    if (bookmarks.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No bookmarks yet.")
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(bookmarks) { article ->
                ArticleCard(article = article, onClick = { onArticleClick(article) })
            }
        }
    }
}
