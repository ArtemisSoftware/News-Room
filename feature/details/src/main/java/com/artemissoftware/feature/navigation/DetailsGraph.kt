package com.artemissoftware.feature.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.feature.details.DetailsScreen
import com.core.ui.navigation.BaseDestination
import com.core.ui.navigation.GalleryUI
import com.core.ui.navigation.ProductParameters
import com.core.ui.navigation.ProductParametersType

private const val DETAILS_GRAPH = "details_graph"

fun NavController.navigateToDetailsGraph() = navigate(DETAILS_GRAPH)

fun NavController.lolo(person: ProductParameters) = navigate(DetailRoute.Details.withCustomArgs(person))

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
        route = "details/argument",
        arguments = listOf(
            navArgument(
                name = "argument",
            ) {
                type = ProductParametersType
            },
        ),
    )
}
