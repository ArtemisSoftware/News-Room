package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.artemissoftware.feature.navigation.SEARCH_ROUTE
import com.artemissoftware.feature.navigation.bookmarkScreen
import com.artemissoftware.feature.navigation.searchScreen

const val HOME_GRAPH = "home_graph"
const val HOME_ROUTE = "home"

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH,
        startDestination = SEARCH_ROUTE,
    ) {
        searchScreen()
        bookmarkScreen()
//        composable(route = SEARCH_ROUTE) {
//            Box(modifier = Modifier.fillMaxSize().background(color = Color.Cyan))
//        }
//
//        composable(route = BottomBarItem.Profile.route) {
//            NGGenericScreen(
//                name = BottomBarItem.Profile.route,
//                onClick = { }
//            )
//        }
//
//        composable(route = BottomBarItem.Settings.route) {
//            NGGenericScreen(
//                name = BottomBarItem.Settings.route,
//                onClick = { }
//            )
//        }

        // detailsNavigationGraph(navController = navController)
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
