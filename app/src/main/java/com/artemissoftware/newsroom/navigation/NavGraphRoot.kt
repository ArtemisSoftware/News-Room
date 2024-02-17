package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.feature.navigation.OnboardingDestinationScreen
import com.artemissoftware.feature.navigation.detailsGraph
import com.artemissoftware.feature.navigation.onboardingRoutes

@Composable
fun NavGraphRoot(
    navController: NavHostController,
    startDestination: String = OnboardingDestinationScreen.Onboarding.route,
) {
    NavHost(
        startDestination = startDestination,
        navController = navController,
    ) {
        onboardingRoutes()

        detailsGraph(popBackStack = navController::popBackStack)
    }
}
