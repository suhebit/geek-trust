package com.assignment.news.domain.usecase

import com.assignment.news.domain.model.Article
import com.assignment.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for bookmarking an article.
 *
 * @param newsRepository The repository for news articles.
 */
class BookmarkArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * Bookmarks an article.
     *
     * @param article The article to bookmark.
     */
    suspend operator fun invoke(article: Article) = newsRepository.bookmarkArticle(article)
}