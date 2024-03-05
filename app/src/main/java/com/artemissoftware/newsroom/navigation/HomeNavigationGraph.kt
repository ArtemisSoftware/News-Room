package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.feature.navigation.NEWS_ROUTE
import com.artemissoftware.feature.navigation.bookmarkScreen
import com.artemissoftware.feature.navigation.detailsGraph
import com.artemissoftware.feature.navigation.navigateToDetail
import com.artemissoftware.feature.navigation.navigateToDetailsGraph
import com.artemissoftware.feature.navigation.newsScreen
import com.artemissoftware.feature.navigation.searchScreen
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination

const val HOME_GRAPH = "home_graph"
const val HOME_ROUTE = "home"

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    navigateToTopLevel: (TopLevelDestination) -> Unit,
) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH,
        startDestination = NEWS_ROUTE,
    ) {
        newsScreen(
            navigateToSearch = {
                navigateToTopLevel(BottomBarDestinations.search)
            },
            navigateToDetails = {
                navController.navigateToDetail(article = it)
            },
        )

        bookmarkScreen(
            navigateToDetails = {
                navController.navigateToDetailsGraph()
            },
        )

        searchScreen(
            navigateToDetails = {
                navController.navigateToDetail(article = it)
            },
        )

        detailsGraph(
            popBackStack = {
                navController.popBackStack()
            },
        )
    }
}

// fun NavController.navigateToHomeGraph() = navigate(HOME_GRAPH)
//
// fun NavGraphBuilder.homeGraph() {
//    navigation(
//        route = HOME_GRAPH,
//        startDestination = HomeRoute.Home.route,
//    ) {
//        composable(route = HomeRoute.Home.route) {
//            HomeScreen()
//        }
//    }
// }
//
// internal sealed class HomeRoute(val route: String) {
//    object Home : HomeRoute(route = HOME_ROUTE)
// }
