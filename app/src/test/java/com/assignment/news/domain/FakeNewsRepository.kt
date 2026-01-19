package com.assignment.news.domain

import androidx.paging.PagingData
import com.assignment.news.domain.model.Article
import com.assignment.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeNewsRepository : NewsRepository {

    private val bookmarkedArticles = mutableListOf<Article>()

    override fun getNewsFeed(): Flow<PagingData<Article>> {
        return flowOf(PagingData.from(emptyList()))
    }

    override fun searchNews(query: String): Flow<PagingData<Article>> {
        return flowOf(PagingData.from(emptyList()))
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return flowOf(bookmarkedArticles)
    }

    override suspend fun bookmarkArticle(article: Article) {
        bookmarkedArticles.add(article)
    }

    override suspend fun unbookmarkArticle(article: Article) {
        bookmarkedArticles.remove(article)
    }

    override suspend fun isBookmarked(url: String): Boolean {
        return bookmarkedArticles.any { it.url == url }
    }
}