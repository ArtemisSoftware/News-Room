package com.artemissoftware.newsroom.navigation

import com.artemissoftware.feature.navigation.BookmarkRoute
import com.artemissoftware.feature.navigation.NEWS_ROUTE
import com.artemissoftware.feature.navigation.SearchRoute
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination
import com.core.ui.R

object BottomBarDestinations {

    val search = TopLevelDestination(
        icon = R.drawable.ic_search,
        text = R.string.search,
        route = SearchRoute.Search.route,
    )

    val bookmark = TopLevelDestination(
        icon = R.drawable.ic_bookmark,
        text = R.string.bookmark,
        route = BookmarkRoute.Bookmark.route,
    )

    val news = TopLevelDestination(
        icon = R.drawable.ic_home,
        text = R.string.home,
        route = NEWS_ROUTE,
    )

    val destinations = listOf(news, bookmark, search)
}
