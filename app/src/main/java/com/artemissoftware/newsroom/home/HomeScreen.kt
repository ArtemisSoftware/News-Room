package com.artemissoftware.newsroom.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artemissoftware.newsroom.NRAppState
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.NRNavigationBar
import com.artemissoftware.newsroom.navigation.HomeNavGraph
import com.artemissoftware.newsroom.rememberNRAppState

@Composable
fun HomeScreen(
    appState: NRAppState = rememberNRAppState(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NRNavigationBar(
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentTopLevelDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
            )
        },
    ) {
        val bottomPadding = it.calculateBottomPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding),
        ) {
            HomeNavGraph(
                navigateToTopLevel = appState::navigateToTopLevelDestination,
                navController = appState.navController,
            )
        }
    }
}
