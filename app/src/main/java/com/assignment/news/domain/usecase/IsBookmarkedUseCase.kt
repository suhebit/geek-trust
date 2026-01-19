package com.assignment.news.domain.usecase

import com.assignment.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for checking if an article is bookmarked.
 *
 * @param newsRepository The repository for news articles.
 */
class IsBookmarkedUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * Checks if an article is bookmarked.
     *
     * @param url The URL of the article.
     * @return True if the article is bookmarked, false otherwise.
     */
    suspend operator fun invoke(url: String) = newsRepository.isBookmarked(url)
}