package com.artemissoftware.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.artemissoftware.feature.onboarding.OnBoardingScreen

const val ONBOARDING_ROUTE = "onboarding"

fun NavGraphBuilder.onboardingRoutes() {
    composable(
        route = OnboardingScreen.Onboarding.route,
    ) {
        OnBoardingScreen()
    }
}

internal sealed class OnboardingScreen(val route: String) {

    object Onboarding : OnboardingScreen(route = ONBOARDING_ROUTE)
}
