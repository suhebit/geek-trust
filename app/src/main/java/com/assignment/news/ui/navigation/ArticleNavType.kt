package com.assignment.news.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.assignment.news.domain.model.Article
import com.google.gson.Gson

/**
 * A [NavType] for [Article] objects.
 */
class ArticleNavType : NavType<Article>(isNullableAllowed = false) {
    /**
     * Gets an [Article] from a [Bundle].
     */
    override fun get(bundle: Bundle, key: String): Article? {
        return bundle.getParcelable(key)
    }

    /**
     * Parses an [Article] from a string.
     */
    override fun parseValue(value: String): Article {
        return Gson().fromJson(value, Article::class.java)
    }

    /**
     * Puts an [Article] into a [Bundle].
     */
    override fun put(bundle: Bundle, key: String, value: Article) {
        bundle.putParcelable(key, value)
    }
}