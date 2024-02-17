package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.details.DetailsScreen

private const val DETAILS_GRAPH = "details_graph"

fun NavController.navigateToDetailsGraph() = navigate(DETAILS_GRAPH)

fun NavGraphBuilder.detailsGraph(
    popBackStack: () -> Unit,
) {
    navigation(
        route = DETAILS_GRAPH,
        startDestination = DetailScreen.Details.route,
    ) {
        composable(route = DetailScreen.Details.route) {
            DetailsScreen(
                popBackStack = popBackStack,
            )
        }
    }
}

sealed class DetailScreen(val route: String) {
    object Details : DetailScreen(route = "details")
}
