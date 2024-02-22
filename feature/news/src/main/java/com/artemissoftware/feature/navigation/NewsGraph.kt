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
        startDestination = Screen.News.route,
    ) {
        composable(route = Screen.News.route) {
            NewsScreen(
                navigateToSearch = {

                },
                navigateToDetails = {

                },
            )
        }
    }
}

internal sealed class Screen(val route: String) {
    object News : Screen(route = "news")
}