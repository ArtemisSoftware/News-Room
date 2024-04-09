package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.search.SearchScreen
import com.artemissoftware.newsroom.core.model.Article

private const val SEARCH_GRAPH = "search_graph"

fun NavController.navigateToSearchGraph(navOptions: NavOptions) = navigate(SEARCH_GRAPH, navOptions)

fun NavGraphBuilder.searchNavGraph(
    navigateToDetails: (Article) -> Unit,
) {
    navigation(
        route = SEARCH_GRAPH,
        startDestination = SearchRoute.Search.route,
    ) {
        composable(route = SearchRoute.Search.route) {
            SearchScreen(
                navigateToDetails = { article ->
                    navigateToDetails(article)
                },
            )
        }
    }
}

sealed class SearchRoute(val route: String) {
    object Search : SearchRoute(route = "search")
}
