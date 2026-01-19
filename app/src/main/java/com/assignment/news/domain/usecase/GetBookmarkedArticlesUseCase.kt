package com.assignment.news.domain.usecase

import com.assignment.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for getting the bookmarked articles.
 *
 * @param newsRepository The repository for news articles.
 */
class GetBookmarkedArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * Gets the bookmarked articles.
     *
     * @return A flow of a list of bookmarked articles.
     */
    operator fun invoke() = newsRepository.getBookmarkedArticles()
}