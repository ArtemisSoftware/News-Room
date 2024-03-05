package com.artemissoftware.feature.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.feature.details.DetailsScreen
import com.artemissoftware.navigation.ArticleNavType
import com.artemissoftware.navigation.BaseDestination
import com.artemissoftware.newsroom.core.model.Article

private const val DETAILS_GRAPH = "details_graph"

fun NavController.navigateToDetailsGraph() = navigate(DETAILS_GRAPH)

fun NavController.navigateToDetail(article: Article) = navigate(DetailRoute.Details.withCustomArgs(article))

fun NavGraphBuilder.detailsGraph(
    popBackStack: () -> Unit,
) {
    navigation(
        route = DETAILS_GRAPH,
        startDestination = "details",
    ) {
        composable(
            route = DetailRoute.Details.getRouteInFull(),
            arguments = DetailRoute.Details.arguments,
        ) {
            val argumentValue = it.arguments?.getString("argument")
            DetailsScreen(
                popBackStack = popBackStack,
            )
        }
    }
}

internal sealed class DetailRoute(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
) : BaseDestination(
    route = route,
    customArguments = arguments,
) {
    object Details : DetailRoute(
        route = "details",
        arguments = listOf(
            navArgument(name = NavArguments.article) {
                type = ArticleNavType
            },
        ),
    )
}
