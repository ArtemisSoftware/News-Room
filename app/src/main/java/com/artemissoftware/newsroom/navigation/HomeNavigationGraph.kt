package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.feature.navigation.NEWS_ROUTE
import com.artemissoftware.feature.navigation.bookmarkScreen
import com.artemissoftware.feature.navigation.detailsGraph
import com.artemissoftware.feature.navigation.lolo
import com.artemissoftware.feature.navigation.navigateToDetailsGraph
import com.artemissoftware.feature.navigation.newsScreen
import com.artemissoftware.feature.navigation.searchScreen
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination
import com.core.ui.navigation.GalleryUI
import com.core.ui.navigation.ProductParameters

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
                //navigateToTopLevel(BottomBarDestinations.search)
                //navController.navigateToDetailsGraph()
                //-navController.navigate("details?argument=456")
                navController.lolo(ProductParameters(12))

            },
            navigateToDetails = {
                navController.navigateToDetailsGraph()
            },
        )

        bookmarkScreen(
            navigateToDetails = {
                navController.navigateToDetailsGraph()
            },
        )

        searchScreen(
            navigateToDetails = {
                navController.navigateToDetailsGraph()
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
