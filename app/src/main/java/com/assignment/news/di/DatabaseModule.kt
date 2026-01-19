package com.assignment.news.di

import android.content.Context
import androidx.room.Room
import com.assignment.news.data.local.ArticleDao
import com.assignment.news.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Hilt module for providing database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the [NewsDatabase] instance.
     *
     * @param context The application context.
     * @return The [NewsDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news.db"
        ).build()
    }

    /**
     * Provides the [ArticleDao] instance.
     *
     * @param newsDatabase The [NewsDatabase] instance.
     * @return The [ArticleDao] instance.
     */
    @Provides
    @Singleton
    fun provideArticleDao(newsDatabase: NewsDatabase): ArticleDao {
        return newsDatabase.articleDao()
    }
}
