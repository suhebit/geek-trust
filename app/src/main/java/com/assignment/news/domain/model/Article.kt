package com.assignment.news.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents an article.
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
@Parcelize
data class Article(
    val source: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) : Parcelable
