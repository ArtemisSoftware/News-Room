package com.artemissoftware.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.artemissoftware.feature.onboarding.OnBoardingScreen

fun NavGraphBuilder.onboardingRoutes() {
    composable(
        route = OnboardingDestinationScreen.Onboarding.route,
    ) {
        OnBoardingScreen()
    }
}
