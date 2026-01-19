package com.assignment.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.assignment.news.data.local.ArticleDao
import com.assignment.news.data.mapper.toDomain
import com.assignment.news.data.mapper.toEntity
import com.assignment.news.data.remote.NewsApiService
import com.assignment.news.domain.model.Article
import com.assignment.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * The implementation of the [NewsRepository].
 *
 * @param newsApiService The remote API service for fetching news.
 * @param articleDao The data access object for the articles table.
 */
class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val articleDao: ArticleDao
) : NewsRepository {

    /**
     * Gets the news feed.
     */
    override fun getNewsFeed(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApiService) }
        ).flow
    }

    /**
     * Searches for news.
     *
     * @param query The search query.
     */
    override fun searchNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchNewsPagingSource(newsApiService, query) }
        ).flow
    }

    /**
     * Gets the bookmarked articles.
     */
    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookmarkedArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    /**
     * Bookmarks an article.
     *
     * @param article The article to bookmark.
     */
    override suspend fun bookmarkArticle(article: Article) {
        articleDao.bookmarkArticle(article.toEntity())
    }

    /**
     * Unbookmarks an article.
     *
     * @param article The article to unbookmark.
     */
    override suspend fun unbookmarkArticle(article: Article) {
        articleDao.unbookmarkArticle(article.toEntity())
    }

    /**
     * Checks if an article is bookmarked.
     *
     * @param url The URL of the article.
     * @return True if the article is bookmarked, false otherwise.
     */
    override suspend fun isBookmarked(url: String): Boolean {
        return articleDao.getArticleByUrl(url) != null
    }
}