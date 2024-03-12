package com.artemissoftware.newsroom.core.designsystem.composables

import com.artemissoftware.newsroom.core.designsystem.R
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination

internal object PreviewData {

    val destinationHome = TopLevelDestination(
        icon = R.drawable.ic_example_placeholder,
        text = R.string.design_system_text_1,
        route = "route_1",
    )

    val listBottomNavigationItem = listOf(
        TopLevelDestination(icon = R.drawable.ic_example_placeholder, text = R.string.design_system_text_1, route = "route_1"),
        TopLevelDestination(icon = R.drawable.ic_example_placeholder, text = R.string.design_system_text_2, route = "route_2"),
    )
}
