package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.bookmark.BookmarkScreen

const val BOOKMARK_GRAPH = "bookmark_graph"

fun NavController.navigateToBookmarkGraph(navOptions: NavOptions) = navigate(BOOKMARK_GRAPH, navOptions)

fun NavGraphBuilder.bookmarkNavGraph(
    navigateToDetails: (Int) -> Unit,
) {
    navigation(
        route = BOOKMARK_GRAPH,
        startDestination = BookmarkRoute.Bookmark.route,
    ) {
        composable(route = BookmarkRoute.Bookmark.route) {
            BookmarkScreen(
                navigateToDetails = navigateToDetails,
            )
        }
    }
}

sealed class BookmarkRoute(val route: String) {
    object Bookmark : BookmarkRoute(route = "bookmark")
}
