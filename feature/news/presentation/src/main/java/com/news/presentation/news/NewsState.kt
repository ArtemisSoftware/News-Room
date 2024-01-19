package com.news.presentation.news

import com.core.domain.models.Article
import com.news.presentation.news.models.Topic

data class NewsState(
    val articles: List<Article> = emptyList(),
    val topics: List<Topic> = emptyList(),

    val newsTicker: String = "",
    val isLoading: Boolean = false,
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0,
)
