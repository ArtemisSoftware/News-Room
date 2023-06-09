package com.news.presentation.news

import com.news.domain.models.Article

data class NewsState(
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val articles: List<Article> = emptyList(),
    val searchedArticles: List<Article> = emptyList(),
)
