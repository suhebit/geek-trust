package com.assignment.news.domain.usecase

import com.assignment.news.domain.FakeNewsRepository
import com.assignment.news.domain.model.Article
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class IsBookmarkedUseCaseTest {

    private lateinit var isBookmarkedUseCase: IsBookmarkedUseCase
    private lateinit var fakeNewsRepository: FakeNewsRepository

    @Before
    fun setUp() {
        fakeNewsRepository = FakeNewsRepository()
        isBookmarkedUseCase = IsBookmarkedUseCase(fakeNewsRepository)
    }

    @Test
    fun `invoke returns true for bookmarked article`() = runBlocking {
        // Given
        val article = Article(
            source = "source",
            author = "author",
            title = "title",
            description = "description",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = "publishedAt",
            content = "content"
        )
        fakeNewsRepository.bookmarkArticle(article)

        // When
        val isBookmarked = isBookmarkedUseCase(article.url)

        // Then
        assert(isBookmarked)
    }

    @Test
    fun `invoke returns false for non-bookmarked article`() = runBlocking {
        // Given
        val url = "url"

        // When
        val isBookmarked = isBookmarkedUseCase(url)

        // Then
        assert(!isBookmarked)
    }
}