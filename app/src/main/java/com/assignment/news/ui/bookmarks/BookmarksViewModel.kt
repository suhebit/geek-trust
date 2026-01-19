package com.assignment.news.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.news.domain.model.Article
import com.assignment.news.domain.usecase.GetBookmarkedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * The ViewModel for the bookmarks screen.
 *
 * @param getBookmarkedArticlesUseCase The use case for getting the bookmarked articles.
 */
@HiltViewModel
class BookmarksViewModel @Inject constructor(
    getBookmarkedArticlesUseCase: GetBookmarkedArticlesUseCase
) : ViewModel() {

    /**
     * A flow of the bookmarked articles.
     */
    val bookmarks = getBookmarkedArticlesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
