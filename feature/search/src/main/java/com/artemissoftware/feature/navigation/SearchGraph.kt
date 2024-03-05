package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.search.SearchScreen
import com.artemissoftware.newsroom.core.model.Article

private const val SEARCH_GRAPH = "search_graph"
const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearchGraph(navOptions: NavOptions) = navigate(SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen(
    navigateToDetails: (Article) -> Unit,
) {
    composable(route = SEARCH_ROUTE) {
        SearchScreen(
            navigateToDetails = { article ->
                navigateToDetails(article)
            },
        )
    }
}

//fun NavGraphBuilder.searchGraph() {
//    navigation(
//        route = SEARCH_GRAPH,
//        startDestination = SearchRoute.Search.route,
//    ) {
//        composable(route = SearchRoute.Search.route) {
//            SearchScreen(
//                navigateToDetails = { url ->
//                },
//            )
//        }
//    }
//}

internal sealed class SearchRoute(val route: String) {
    object Search : SearchRoute(route = SEARCH_ROUTE)
}
