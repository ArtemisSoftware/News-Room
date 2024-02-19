package com.artemissoftware.feature.search

internal object PreviewData {

    val searchStateEmpty = SearchState(
        searchQuery = "",
        isSearching = false,
        historyItems = emptyList(),
        articles = emptyList(),
    )

    val searchState = SearchState(
        searchQuery = "Debate",
        isSearching = true,
        historyItems = listOf("War", "Fashion"),
        articles = emptyList(),
    )
}
