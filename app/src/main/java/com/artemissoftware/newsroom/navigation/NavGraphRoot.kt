package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.navigation.ONBOARDING_GRAPH
import com.artemissoftware.feature.navigation.onboardingGraph
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
        //onboardingRoutes()

        // detailsGraph(popBackStack = navController::popBackStack)
        onboardingGraph(
            navigateToHome = {
                navController.navigate(HOME_GRAPH) {
                    popUpTo(ONBOARDING_GRAPH) { inclusive = true }
                }
            }
        )

        composable(route = HOME_GRAPH) {
            HomeScreen()
        }
    }
}
