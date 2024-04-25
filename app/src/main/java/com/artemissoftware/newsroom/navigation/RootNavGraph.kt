package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.feature.navigation.ONBOARDING_GRAPH
import com.artemissoftware.feature.navigation.onboardingNavGraph
import com.artemissoftware.newsroom.home.HomeScreen
import com.artemissoftware.util.extensions.popUpTo

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        startDestination = startDestination,
        navController = navController,
    ) {
        onboardingNavGraph(
            navigateToHome = {
                navController.popUpTo(fromRoute = ONBOARDING_GRAPH, toRoute = HOME_GRAPH)
            },
        )

        composable(route = HOME_GRAPH) {
            HomeScreen()
        }
    }
}
