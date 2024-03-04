package com.artemissoftware.newsroom.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artemissoftware.newsroom.NRAppState
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.NRNavigationBar
import com.artemissoftware.newsroom.navigation.HomeNavigationGraph
import com.artemissoftware.newsroom.rememberNRAppState

@Composable
fun HomeScreen(
    appState: NRAppState = rememberNRAppState(),
) {
/*
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }
*/

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
            HomeNavigationGraph(
                navigateToTopLevel = appState::navigateToTopLevelDestination,
                navController = appState.navController,
            )
        }

//        NavHost(
//            navController = navController,
//            startDestination = Route.HomeScreen.route,
//            modifier = Modifier.padding(bottom = bottomPadding)
//        ) {
//            composable(route = Route.HomeScreen.route) { backStackEntry ->
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(
//                    articles = articles,
//                    navigateToSearch = {
//                        navigateToTab(
//                            navController = navController,
//                            route = Route.SearchScreen.route
//                        )
//                    },
//                    navigateToDetails = { article ->
//                        navigateToDetails(
//                            navController = navController,
//                            article = article
//                        )
//                    },
//                    event = viewModel::onEvent,
//                    state = viewModel.state.value
//                )
//            }
//            composable(route = Route.SearchScreen.route) {
//                val viewModel: SearchViewModel = hiltViewModel()
//                val state = viewModel.state.value
//                OnBackClickStateSaver(navController = navController)
//                SearchScreen(
//                    state = state,
//                    event = viewModel::onEvent,
//                    navigateToDetails = { article ->
//                        navigateToDetails(
//                            navController = navController,
//                            article = article
//                        )
//                    }
//                )
//            }
//            composable(route = Route.DetailsScreen.route) {
//                val viewModel: DetailsViewModel = hiltViewModel()
//                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
//                    ?.let { article ->
//                        DetailsScreen(
//                            article = article,
//                            event = viewModel::onEvent,
//                            navigateUp = { navController.navigateUp() },
//                            sideEffect = viewModel.sideEffect
//                        )
//                    }
//
//            }
//            composable(route = Route.BookmarkScreen.route) {
//                val viewModel: BookmarkViewModel = hiltViewModel()
//                val state = viewModel.state.value
//                OnBackClickStateSaver(navController = navController)
//                BookmarkScreen(
//                    state = state,
//                    navigateToDetails = { article ->
//                        navigateToDetails(
//                            navController = navController,
//                            article = article
//                        )
//                    }
//                )
//            }
//        }
    }
}

// @Composable
// fun OnBackClickStateSaver(navController: NavController) {
//    BackHandler(true) {
//        navigateToTab(
//            navController = navController,
//            route = Route.HomeScreen.route
//        )
//    }
// }
//
// private fun navigateToTab(navController: NavController, route: String) {
//    navController.navigate(route) {
//        navController.graph.startDestinationRoute?.let { screen_route ->
//            popUpTo(screen_route) {
//                saveState = true
//            }
//        }
//        launchSingleTop = true
//        restoreState = true
//    }
// }
//
// private fun navigateToDetails(navController: NavController, article: Article) {
//    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
//    navController.navigate(
//        route = Route.DetailsScreen.route
//    )
// }
