package com.assignment.news.data.mapper

import com.assignment.news.data.dto.ArticleDto
import com.assignment.news.data.local.ArticleEntity
import com.assignment.news.domain.model.Article

/**
 * Converts an [ArticleDto] to a domain [Article].
 */
fun ArticleDto.toDomain(): Article {
    return Article(
        source = source.name,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

/**
 * Converts a domain [Article] to an [ArticleEntity].
 */
fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

/**
 * Converts an [ArticleEntity] to a domain [Article].
 */
fun ArticleEntity.toDomain(): Article {
    return Article(
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}
