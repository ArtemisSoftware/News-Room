package com.artemissoftware.newsroom.navigation

import com.artemissoftware.feature.navigation.BookmarkRoute
import com.artemissoftware.feature.navigation.NewsRoute
import com.artemissoftware.feature.navigation.SearchRoute
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination
import com.core.presentation.R
import com.core.ui.R as CoreUiR

object BottomBarDestinations {

    val search = TopLevelDestination(
        icon = CoreUiR.drawable.ic_search,
        text = CoreUiR.string.search,
        route = SearchRoute.Search.route,
    )

    val bookmark = TopLevelDestination(
        icon = CoreUiR.drawable.ic_bookmark,
        text = R.string.bookmark,
        route = BookmarkRoute.Bookmark.route,
    )

    val news = TopLevelDestination(
        icon = CoreUiR.drawable.ic_home,
        text = R.string.home,
        route = NewsRoute.News.fullRoute(),
    )

    val destinations = listOf(news, bookmark, search)
}
