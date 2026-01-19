package com.assignment.news.data.remote

import com.assignment.news.data.API_KEY
import com.assignment.news.data.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The remote API service for fetching news.
 */
interface NewsApiService {

    /**
     * Fetches the top headlines from the news API.
     *
     * @param country The country to fetch headlines for.
     * @param page The page number to fetch.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for the news service.
     * @return A [NewsResponse] containing the top headlines.
     */
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    /**
     * Searches for news articles.
     *
     * @param query The search query.
     * @param page The page number to fetch.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for the news service.
     * @return A [NewsResponse] containing the search results.
     */
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}