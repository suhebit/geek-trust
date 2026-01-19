package com.assignment.news.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Represents an item in the bottom navigation bar.
 *
 * @property route The route of the item.
 * @property icon The icon of the item.
 * @property title The title of the item.
 */
sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    /**
     * The home item.
     */
    object Home : BottomNavItem("news_feed", Icons.Default.Home, "Home")

    /**
     * The bookmarks item.
     */
    object Bookmarks : BottomNavItem("bookmarks", Icons.Default.Bookmark, "Bookmarks")
}
