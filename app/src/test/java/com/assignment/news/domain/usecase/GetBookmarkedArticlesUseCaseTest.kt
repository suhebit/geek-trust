package com.assignment.news.domain.usecase

import app.cash.turbine.test
import com.assignment.news.domain.FakeNewsRepository
import com.assignment.news.domain.model.Article
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetBookmarkedArticlesUseCaseTest {

    private lateinit var getBookmarkedArticlesUseCase: GetBookmarkedArticlesUseCase
    private lateinit var fakeNewsRepository: FakeNewsRepository

    @Before
    fun setUp() {
        fakeNewsRepository = FakeNewsRepository()
        getBookmarkedArticlesUseCase = GetBookmarkedArticlesUseCase(fakeNewsRepository)
    }

    @Test
    fun `invoke returns flow of bookmarked articles`() = runBlocking {
        // Given
        val article1 = Article("url1", "source1", "author1", "title1", "desc1", "img1", "date1", "content1")
        val article2 = Article("url2", "source2", "author2", "title2", "desc2", "img2", "date2", "content2")
        fakeNewsRepository.bookmarkArticle(article1)
        fakeNewsRepository.bookmarkArticle(article2)

        // When & Then
        getBookmarkedArticlesUseCase().test {
            assertEquals(listOf(article1, article2), awaitItem())
            awaitComplete()
        }
    }
}