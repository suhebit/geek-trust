package com.assignment.news.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.news.data.dto.ApiErrorResponse
import com.assignment.news.data.mapper.toDomain
import com.assignment.news.data.remote.NewsApiService
import com.assignment.news.domain.model.Article
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

/**
 * A [PagingSource] that fetches news from the API based on a search query.
 *
 * @param newsApiService The remote API service for fetching news.
 * @param query The search query.
 */
class SearchNewsPagingSource(
    private val newsApiService: NewsApiService,
    private val query: String
) : PagingSource<Int, Article>() {

    /**
     * Loads a page of news.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = newsApiService.searchNews(query = query, page = page, pageSize = params.loadSize)
            val articles = response.articles.map { it.toDomain() }
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            try {
                val errorResponse = Gson().fromJson(
                    exception.response()?.errorBody()?.charStream(),
                    ApiErrorResponse::class.java
                )
                LoadResult.Error(IOException(errorResponse.message))
            } catch (e: Exception) {
                LoadResult.Error(exception)
            }
        }
    }

    /**
     * Gets the refresh key for the current state.
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}