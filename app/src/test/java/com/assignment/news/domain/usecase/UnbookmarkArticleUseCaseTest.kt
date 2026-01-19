package com.assignment.news.domain.usecase

import com.assignment.news.domain.FakeNewsRepository
import com.assignment.news.domain.model.Article
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UnbookmarkArticleUseCaseTest {

    private lateinit var unbookmarkArticleUseCase: UnbookmarkArticleUseCase
    private lateinit var fakeNewsRepository: FakeNewsRepository

    @Before
    fun setUp() {
        fakeNewsRepository = FakeNewsRepository()
        unbookmarkArticleUseCase = UnbookmarkArticleUseCase(fakeNewsRepository)
    }

    @Test
    fun `invoke unbookmarks article`() = runBlocking {
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
        unbookmarkArticleUseCase(article)

        // Then
        assert(!fakeNewsRepository.isBookmarked(article.url))
    }
}