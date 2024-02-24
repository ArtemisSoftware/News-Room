package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation

@Composable
fun NavGraphRoot(
    navController: NavHostController,
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        startDestination = HOME_GRAPH,
        navController = navController,
        route = HOME_ROUTE,
    ) {
        // onboardingRoutes()

        // detailsGraph(popBackStack = navController::popBackStack)

        homeGraph()
    }
}
