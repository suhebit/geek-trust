package com.assignment.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.assignment.news.domain.model.Article
import com.assignment.news.domain.usecase.GetNewsFeedUseCase
import com.assignment.news.domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for the news feed screen.
 *
 * @param getNewsFeedUseCase The use case for getting the news feed.
 * @param searchNewsUseCase The use case for searching for news.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsFeedUseCase: GetNewsFeedUseCase,
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    /**
     * The news feed to be displayed.
     */
    private val _newsFeed = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val newsFeed: StateFlow<PagingData<Article>> = _newsFeed.asStateFlow()

    /**
     * The current search query.
     */
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        getNewsFeedUseCase()
                    } else {
                        searchNewsUseCase(query)
                    }
                }
                .cachedIn(viewModelScope)
                .collect { _newsFeed.value = it }
        }
    }

    /**
     * Called when the search query changes.
     *
     * @param query The new search query.
     */
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}