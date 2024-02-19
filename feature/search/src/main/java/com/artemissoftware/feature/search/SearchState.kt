package com.artemissoftware.feature.search

import com.artemissoftware.newsroom.core.model.Article

data class SearchState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val historyItems: List<String> = emptyList(),
    val articles: List<Article> = emptyList(),
    // val articles: Flow<PagingData<Article>>? = null
)
