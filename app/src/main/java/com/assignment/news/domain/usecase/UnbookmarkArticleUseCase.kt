package com.assignment.news.domain.usecase

import com.assignment.news.domain.model.Article
import com.assignment.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for unbookmarking an article.
 *
 * @param newsRepository The repository for news articles.
 */
class UnbookmarkArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * Unbookmarks an article.
     *
     * @param article The article to unbookmark.
     */
    suspend operator fun invoke(article: Article) = newsRepository.unbookmarkArticle(article)
}