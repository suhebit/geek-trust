package com.assignment.news.domain.usecase

import com.assignment.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for searching for news.
 *
 * @param newsRepository The repository for news articles.
 */
class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * Searches for news.
     *
     * @param query The search query.
     * @return A flow of paging data of articles.
     */
    operator fun invoke(query: String) = newsRepository.searchNews(query)
}