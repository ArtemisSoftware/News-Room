package com.core.ui.composables

import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.model.Article
import com.artemissoftware.newsroom.core.model.Source
import com.core.ui.composables.bottomnavigation.BottomNavigationItem

internal object PreviewData {

    val article = Article(
        author = "",
        content = "",
        description = "",
        publishedAt = "2 hours",
        source = Source(name = "BBC"),
        title = "Her train broke down. Her phone died. And then she met her Saver in a",
        url = "",
        urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg",
    )

    val articleList = listOf(
        article,
        article,
        article,
        article,
        article,
        article,
        article,
        article,
        article,
        article,
        article,
        article,
    )

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
