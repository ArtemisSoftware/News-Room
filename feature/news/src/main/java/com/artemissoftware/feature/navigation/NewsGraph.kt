package com.artemissoftware.feature.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.artemissoftware.feature.news.NewsScreen
import com.artemissoftware.newsroom.core.model.Article

private const val NEWS_GRAPH = "news_graph"
const val NEWS_ROUTE = "news"

fun NavController.navigateToNewsGraph(navOptions: NavOptions) = navigate(NEWS_ROUTE, navOptions)

fun NavGraphBuilder.newsScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    composable(route = NEWS_ROUTE) {
        NewsScreen(
            navigateToSearch = navigateToSearch,
            navigateToDetails = { article ->
                navigateToDetails(article)
            },
        )
    }
}

// fun NavGraphBuilder.newsGraph() {
//    navigation(
//        route = NEWS_GRAPH,
//        startDestination = NewsScreen.News.route,
//    ) {
//        composable(route = NewsScreen.News.route) {
//            NewsScreen(
//                navigateToSearch = {
//                },
//                navigateToDetails = {
//                },
//            )
//        }
//    }
// }

internal sealed class NewsScreen(
    route: String,
    customArguments: List<NamedNavArgument> = emptyList(),
) /*: com.artemissoftware.navigation.BaseDestination(route = route, customArguments = customArguments)*/ {
    object News : NewsScreen(route = NEWS_ROUTE)
    // object Pictures : Destination(route = "PICTURES", listOf(CustomArguments(key = NavigationArguments.GALLERY_ID, type = GalleryUINavType())))
}
