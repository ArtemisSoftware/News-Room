package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.bookmark.BookmarkScreen

private const val BOOKMARK_GRAPH = "bookmark_graph"
const val BOOKMARK_ROUTE = "bookmark"

fun NavController.navigateToBookmarkGraph(navOptions: NavOptions) = navigate(BOOKMARK_ROUTE, navOptions)

fun NavGraphBuilder.bookmarkScreen() {
    composable(route = BOOKMARK_ROUTE) {
        BookmarkScreen()
    }
}

fun NavGraphBuilder.bookmarkGraph(
    popBackStack: () -> Unit,
) {
    navigation(
        route = BOOKMARK_GRAPH,
        startDestination = Screen.Bookmark.route,
    ) {
        composable(route = Screen.Bookmark.route) {
            BookmarkScreen()
        }
    }
}

internal sealed class Screen(val route: String) {
    object Bookmark : Screen(route = BOOKMARK_ROUTE)
}
