package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.news.NewsScreen

private const val NEWS_GRAPH = "news_graph"

fun NavController.navigateToNewsGraph() = navigate(NEWS_GRAPH)

fun NavGraphBuilder.newsGraph() {
    navigation(
        route = NEWS_GRAPH,
        startDestination = NewsScreen.News.route,
    ) {
        composable(route = NewsScreen.News.route) {
            NewsScreen(
                navigateToSearch = {
                },
                navigateToDetails = {
                },
            )
        }
    }
}

internal sealed class NewsScreen(val route: String) {
    object News : NewsScreen(route = "news")
}
