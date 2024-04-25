package com.artemissoftware.feature.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.feature.details.DetailsScreen
import com.artemissoftware.navigation.BaseDestination
import com.artemissoftware.newsroom.core.model.Article

private const val DETAILS_GRAPH = "details_graph"

fun NavController.navigateToDetailsGraph() = navigate(DETAILS_GRAPH)

fun NavController.navigateToDetail(article: Article) = navigate(DetailRoute.Details.withCustomArgs(article, null))
fun NavController.navigateToDetail(id: Int) = navigate(DetailRoute.Details.withCustomArgs(null, id))

fun NavGraphBuilder.detailsGraph(
    popBackStack: () -> Unit,
) {
    navigation(
        route = DETAILS_GRAPH,
        startDestination = DetailRoute.Details.fullRoute(),
    ) {
        composable(
            route = DetailRoute.Details.fullRoute(),
            arguments = DetailRoute.Details.arguments,
        ) {
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
            navArgument(name = NavArguments.ARTICLE) {
                type = ArticleNavType
                nullable = true
            },
            navArgument(name = NavArguments.ARTICLE_ID) {
                type = NavType.IntType
                defaultValue = -1
            },
        ),
    )
}
