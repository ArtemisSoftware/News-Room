package com.artemissoftware.feature.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.news.NewsScreen
import com.artemissoftware.navigation.BaseDestination
import com.artemissoftware.newsroom.core.model.Article

private const val NEWS_GRAPH = "news_graph"

fun NavController.navigateToNewsGraph(navOptions: NavOptions) = navigate(NEWS_GRAPH, navOptions)

fun NavGraphBuilder.newsNavGraph(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    navigation(
        route = NEWS_GRAPH,
        startDestination = NewsRoute.News.fullRoute(),
    ) {
        composable(route = NewsRoute.News.fullRoute()) {
            NewsScreen(
                navigateToSearch = navigateToSearch,
                navigateToDetails = { article ->
                    navigateToDetails(article)
                },
            )
        }
    }
}

sealed class NewsRoute(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
) : BaseDestination(route = route, customArguments = arguments) {
    object News : NewsRoute(route = "news")
}
