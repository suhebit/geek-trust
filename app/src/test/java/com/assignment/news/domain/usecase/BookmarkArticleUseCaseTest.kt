package com.assignment.news.domain.usecase

import com.assignment.news.domain.FakeNewsRepository
import com.assignment.news.domain.model.Article
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BookmarkArticleUseCaseTest {

    private lateinit var bookmarkArticleUseCase: BookmarkArticleUseCase
    private lateinit var fakeNewsRepository: FakeNewsRepository

    @Before
    fun setUp() {
        fakeNewsRepository = FakeNewsRepository()
        bookmarkArticleUseCase = BookmarkArticleUseCase(fakeNewsRepository)
    }

    @Test
    fun `invoke bookmarks article`() = runBlocking {
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

        // When
        bookmarkArticleUseCase(article)

        // Then
        assert(fakeNewsRepository.isBookmarked(article.url))
    }
}