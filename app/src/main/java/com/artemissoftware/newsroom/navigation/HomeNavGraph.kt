package com.artemissoftware.newsroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.feature.navigation.NewsRoute
import com.artemissoftware.feature.navigation.bookmarkNavGraph
import com.artemissoftware.feature.navigation.detailsGraph
import com.artemissoftware.feature.navigation.navigateToDetail
import com.artemissoftware.feature.navigation.navigateToDetailsGraph
import com.artemissoftware.feature.navigation.newsNavGraph
import com.artemissoftware.feature.navigation.searchNavGraph
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination

const val HOME_GRAPH = "home_graph"

@Composable
fun HomeNavGraph(
    startDestination: String,
    navController: NavHostController,
    navigateToTopLevel: (TopLevelDestination) -> Unit,
) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH,
        startDestination = startDestination,
    ) {
        newsNavGraph(
            navigateToSearch = {
                navigateToTopLevel(BottomBarDestinations.search)
            },
            navigateToDetails = {
                navController.navigateToDetail(article = it)
            },
        )

        bookmarkNavGraph(
            navigateToDetails = {
                navController.navigateToDetailsGraph()
            },
        )

        searchNavGraph(
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
