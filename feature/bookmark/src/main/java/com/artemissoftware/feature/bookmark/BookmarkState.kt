package com.artemissoftware.feature.bookmark

import com.artemissoftware.newsroom.core.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)
