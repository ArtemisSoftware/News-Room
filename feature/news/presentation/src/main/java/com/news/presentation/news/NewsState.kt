package com.news.presentation.news

import com.news.domain.models.Article
import com.news.presentation.news.models.Topic

data class NewsState(
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val articles: List<Article> = emptyList(),
    val searchedArticles: List<Article> = emptyList(),
    val topics: List<Topic> = emptyList()
)
