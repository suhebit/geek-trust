package com.assignment.news.domain.repository

import androidx.paging.PagingData
import com.assignment.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * The repository for news articles.
 */
interface NewsRepository {

    /**
     * Gets the news feed.
     *
     * @return A [Flow] of [PagingData] of [Article]s.
     */
    fun getNewsFeed(): Flow<PagingData<Article>>

    /**
     * Searches for news.
     *
     * @param query The search query.
     * @return A [Flow] of [PagingData] of [Article]s.
     */
    fun searchNews(query: String): Flow<PagingData<Article>>

    /**
     * Gets the bookmarked articles.
     *
     * @return A [Flow] of a list of bookmarked [Article]s.
     */
    fun getBookmarkedArticles(): Flow<List<Article>>

    /**
     * Bookmarks an article.
     *
     * @param article The article to bookmark.
     */
    suspend fun bookmarkArticle(article: Article)

    /**
     * Unbookmarks an article.
     *
     * @param article The article to unbookmark.
     */
    suspend fun unbookmarkArticle(article: Article)

    /**
     * Checks if an article is bookmarked.
     *
     * @param url The URL of the article.
     * @return True if the article is bookmarked, false otherwise.
     */
    suspend fun isBookmarked(url: String): Boolean
}
