package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.artemissoftware.feature.news.NewsScreen

private const val NEWS_GRAPH = "news_graph"
const val NEWS_ROUTE = "news"

fun NavController.navigateToNewsGraph(navOptions: NavOptions) = navigate(NEWS_ROUTE, navOptions)

fun NavGraphBuilder.newsScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (String) -> Unit,
) {
    composable(route = NEWS_ROUTE) {
        NewsScreen(
            navigateToSearch = navigateToSearch,
            navigateToDetails = { url ->
                navigateToDetails(url)
            },
        )
    }
}

//fun NavGraphBuilder.newsGraph() {
//    navigation(
//        route = NEWS_GRAPH,
//        startDestination = NewsScreen.News.route,
//    ) {
//        composable(route = NewsScreen.News.route) {
//            NewsScreen(
//                navigateToSearch = {
//                },
//                navigateToDetails = {
//                },
//            )
//        }
//    }
//}

internal sealed class NewsScreen(val route: String) {
    object News : NewsScreen(route = NEWS_ROUTE)
}
