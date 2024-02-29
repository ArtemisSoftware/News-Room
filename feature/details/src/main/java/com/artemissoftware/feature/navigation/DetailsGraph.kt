package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.details.DetailsScreen
import com.core.ui.navigation.BaseDestination
import com.core.ui.navigation.CustomArguments
import com.core.ui.navigation.Profile
import com.core.ui.navigation.ProfileArgType

private const val DETAILS_GRAPH = "details_graph"

fun NavController.navigateToDetailsGraph() = navigate(DETAILS_GRAPH)

fun NavController.lolo(person: Profile) = navigate( DetailRoute.Details.withCustomArgs(person))

fun NavGraphBuilder.detailsGraph(
    popBackStack: () -> Unit,
) {
    navigation(
        route = DETAILS_GRAPH,
        startDestination = DetailRoute.Details.fullRoute,
    ) {
        composable(
            route = DetailRoute.Details.fullRoute,
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
    arguments: List<CustomArguments> = emptyList(),
) : BaseDestination(
    route = route,
    customArguments = arguments,
) {
    object Details : DetailRoute(
        route = "details",
        arguments = listOf(CustomArguments(key = "argument", type = ProfileArgType())),
    )
}
