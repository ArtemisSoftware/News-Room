package com.artemissoftware.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.search.SearchScreen


private const val SEARCH_GRAPH = "search_graph"

fun NavController.navigateToSearchGraph() = navigate(SEARCH_GRAPH)

fun NavGraphBuilder.searchGraph() {
    navigation(
        route = SEARCH_GRAPH,
        startDestination = Screen.Search.route,
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen(
                navigateToDetails = { url ->

                },
            )
        }
    }
}

internal sealed class Screen(val route: String) {
    object Search : Screen(route = "details")
}