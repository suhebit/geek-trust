package com.assignment.news.ui.article_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.news.domain.model.Article
import com.assignment.news.domain.usecase.BookmarkArticleUseCase
import com.assignment.news.domain.usecase.IsBookmarkedUseCase
import com.assignment.news.domain.usecase.UnbookmarkArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for the article detail screen.
 *
 * @param bookmarkArticleUseCase The use case for bookmarking an article.
 * @param unbookmarkArticleUseCase The use case for unbookmarking an article.
 * @param isBookmarkedUseCase The use case for checking if an article is bookmarked.
 * @param savedStateHandle The saved state handle for the ViewModel.
 */
@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val bookmarkArticleUseCase: BookmarkArticleUseCase,
    private val unbookmarkArticleUseCase: UnbookmarkArticleUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Whether the article is bookmarked.
     */
    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked = _isBookmarked.asStateFlow()

    /**
     * The article being displayed.
     */
    val article: Article = savedStateHandle.get<Article>("article")!!

    init {
        viewModelScope.launch {
            _isBookmarked.value = isBookmarkedUseCase(article.url)
        }
    }

    /**
     * Toggles the bookmark status of the article.
     */
    fun toggleBookmark() {
        viewModelScope.launch {
            if (_isBookmarked.value) {
                unbookmarkArticleUseCase(article)
            } else {
                bookmarkArticleUseCase(article)
            }
            _isBookmarked.value = !_isBookmarked.value
        }
    }
}