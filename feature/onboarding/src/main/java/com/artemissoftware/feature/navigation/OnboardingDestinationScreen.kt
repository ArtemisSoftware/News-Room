package com.artemissoftware.feature.navigation

sealed class OnboardingDestinationScreen(val route: String) {

    object Onboarding : OnboardingDestinationScreen(route = "onboarding")
}
