package com.artemissoftware.newsroom.core.designsystem.composables

import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.BottomNavigationItem

internal object PreviewData {

    val bottomNavigationItem = BottomNavigationItem(
        icon = R.drawable.ic_home,
        text = R.string.home,
    )

    val listBottomNavigationItem = listOf(
        BottomNavigationItem(icon = R.drawable.ic_home, text = R.string.home),
        BottomNavigationItem(icon = R.drawable.ic_search, text = R.string.search),
        BottomNavigationItem(icon = R.drawable.ic_bookmark, text = R.string.bookmark),
    )
}
