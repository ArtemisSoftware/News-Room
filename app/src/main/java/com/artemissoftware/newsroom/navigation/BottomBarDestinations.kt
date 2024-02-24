package com.artemissoftware.newsroom.navigation

import com.artemissoftware.feature.navigation.BOOKMARK_ROUTE
import com.artemissoftware.feature.navigation.SEARCH_ROUTE
import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination_

object BottomBarDestinations {

    val search = TopLevelDestination_(
        icon = R.drawable.ic_search,
        text = R.string.search,
        route = SEARCH_ROUTE,
    )

    val bookmark = TopLevelDestination_(
        icon = R.drawable.ic_bookmark,
        text = R.string.bookmark,
        route = BOOKMARK_ROUTE,
    )

    val destinations = listOf(bookmark, search)
}
