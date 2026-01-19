package com.assignment.news.di

import com.assignment.news.data.repository.NewsRepositoryImpl
import com.assignment.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Hilt module for providing repository-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds the [NewsRepositoryImpl] to the [NewsRepository] interface.
     *
     * @param newsRepositoryImpl The [NewsRepositoryImpl] instance.
     * @return The [NewsRepository] instance.
     */
    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
