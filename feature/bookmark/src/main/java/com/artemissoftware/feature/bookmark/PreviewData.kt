package com.artemissoftware.feature.bookmark

import com.artemissoftware.newsroom.core.model.Article
import com.artemissoftware.newsroom.core.model.Source

object PreviewData {
    private val article = Article(
        author = "",
        content = "",
        description = "",
        publishedAt = "2 hours",
        source = Source(name = "BBC"),
        title = "Her train broke down. Her phone died. And then she met her Saver in a",
        url = "",
        urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg",
    )

    val bookmarkState = BookmarkState(
        articles = listOf(article, article, article),
    )
}
