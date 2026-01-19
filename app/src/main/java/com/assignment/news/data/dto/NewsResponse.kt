package com.assignment.news.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Represents the response from the news API.
 *
 * @property status The status of the response.
 * @property totalResults The total number of results.
 * @property articles The list of articles.
 */
data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleDto>
)

/**
 * Represents an article from the news API.
 *
 * @property source The source of the article.
 * @property author The author of the article.
 * @property title The title of the article.
 * @property description A description of the article.
 * @property url The URL of the article.
 * @property urlToImage The URL of the image for the article.
 * @property publishedAt The date the article was published.
 * @property content The content of the article.
 */
data class ArticleDto(
    @SerializedName("source") val source: SourceDto,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String?
)

/**
 * Represents the source of an article.
 *
 * @property id The ID of the source.
 * @property name The name of the source.
 */
data class SourceDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)
