package com.artemissoftware.feature.navigation

sealed class Screen(val route: String) {

    object Onboarding : Screen(route = "onboarding")
}
