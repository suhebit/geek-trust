package com.assignment.news.domain.usecase

import com.assignment.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for getting the news feed.
 *
 * @param newsRepository The repository for news articles.
 */
class GetNewsFeedUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * Gets the news feed.
     *
     * @return A flow of paging data of articles.
     */
    operator fun invoke() = newsRepository.getNewsFeed()
}