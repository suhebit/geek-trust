package com.assignment.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.assignment.news.domain.model.Article
import com.assignment.news.ui.article_detail.ArticleDetailScreen
import com.assignment.news.ui.bookmarks.BookmarksScreen
import com.assignment.news.ui.news_feed.NewsFeedScreen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * The navigation graph for the app.
 *
 * @param navController The navigation controller.
 * @param modifier The modifier to be applied to the NavHost.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Home.route) {
            NewsFeedScreen(onArticleClick = { article: Article ->
                val articleJson = Gson().toJson(article)
                val encodedArticleJson = URLEncoder.encode(articleJson, StandardCharsets.UTF_8.toString())
                navController.navigate("article_detail/$encodedArticleJson")
            })
        }
        composable(BottomNavItem.Bookmarks.route) {
            BookmarksScreen(onArticleClick = { article: Article ->
                val articleJson = Gson().toJson(article)
                val encodedArticleJson = URLEncoder.encode(articleJson, StandardCharsets.UTF_8.toString())
                navController.navigate("article_detail/$encodedArticleJson")
            })
        }
        composable(
            route = "article_detail/{article}",
            arguments = listOf(navArgument("article") { type = ArticleNavType() })
        ) { _ ->
            ArticleDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}
