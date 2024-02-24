package com.artemissoftware.newsroom.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.newsroom.home.HomeScreen

const val HOME_GRAPH = "home_graph"
const val HOME_ROUTE = "home"

fun NavController.navigateToHomeGraph() = navigate(HOME_GRAPH)

fun NavGraphBuilder.homeGraph() {
    navigation(
        route = HOME_GRAPH,
        startDestination = HomeRoute.Home.route,
    ) {
        composable(route = HomeRoute.Home.route) {
            HomeScreen()
        }
    }
}

internal sealed class HomeRoute(val route: String) {
    object Home : HomeRoute(route = HOME_ROUTE)
}
