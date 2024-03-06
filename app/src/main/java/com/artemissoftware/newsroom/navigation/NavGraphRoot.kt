package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.navigation.onboardingRoutes
import com.artemissoftware.newsroom.home.HomeScreen

@Composable
fun NavGraphRoot(
    navController: NavHostController,
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        startDestination = startDestination,
        navController = navController,
        route = HOME_ROUTE,
    ) {
        onboardingRoutes()

        // detailsGraph(popBackStack = navController::popBackStack)

        composable(route = HOME_GRAPH) {
            HomeScreen()
        }
    }
}
