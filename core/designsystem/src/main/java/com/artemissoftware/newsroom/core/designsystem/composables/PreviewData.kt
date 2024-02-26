package com.artemissoftware.newsroom.core.designsystem.composables

import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination

internal object PreviewData {

    val destinationHome = TopLevelDestination(
        icon = R.drawable.ic_home,
        text = R.string.home,
        route = "route_1",
    )

    val listBottomNavigationItem = listOf(
        TopLevelDestination(icon = R.drawable.ic_home, text = R.string.home, route = "route_1"),
        TopLevelDestination(icon = R.drawable.ic_search, text = R.string.search, route = "route_2"),
    )
}
