package com.artemissoftware.newsroom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.artemissoftware.feature.navigation.navigateToBookmarkGraph
import com.artemissoftware.feature.navigation.navigateToNewsGraph
import com.artemissoftware.feature.navigation.navigateToSearchGraph
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination
import com.artemissoftware.newsroom.navigation.BottomBarDestinations
import com.artemissoftware.newsroom.navigation.BottomBarDestinations.bookmark
import com.artemissoftware.newsroom.navigation.BottomBarDestinations.news
import com.artemissoftware.newsroom.navigation.BottomBarDestinations.search

class NRAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            search.route -> search
            bookmark.route -> bookmark
            news.route -> news
            else -> null
        }

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = BottomBarDestinations.destinations

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.route}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination.route) {
                search.route -> navController.navigateToSearchGraph(topLevelNavOptions)
                bookmark.route -> navController.navigateToBookmarkGraph(topLevelNavOptions)
                news.route -> navController.navigateToNewsGraph(topLevelNavOptions)
            }
        }
    }
}

@Composable
fun rememberNRAppState(
    navController: NavHostController = rememberNavController(),
): NRAppState {
    return remember(
        navController,
    ) {
        NRAppState(
            navController,
        )
    }
}
