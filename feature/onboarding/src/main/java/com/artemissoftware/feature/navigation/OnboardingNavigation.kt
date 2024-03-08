package com.artemissoftware.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.feature.onboarding.OnBoardingScreen

const val ONBOARDING_GRAPH = "onboarding_graph"

fun NavGraphBuilder.onboardingGraph(
    navigateToHome: () -> Unit
) {
    navigation(
        route = ONBOARDING_GRAPH,
        startDestination = OnboardingRoute.Onboarding.route,
    ) {
        composable(
            route = OnboardingRoute.Onboarding.route,
        ) {
            OnBoardingScreen(
                navigateToHome = navigateToHome
            )
        }
    }
}

internal sealed class OnboardingRoute(val route: String) {
    object Onboarding : OnboardingRoute(route = "onboarding")
}
