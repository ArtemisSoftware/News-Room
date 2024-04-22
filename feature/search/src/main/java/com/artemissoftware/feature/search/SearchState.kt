package com.artemissoftware.feature.search

import androidx.paging.PagingData
import com.artemissoftware.newsroom.core.model.Article
import com.core.ui.composables.DialogData
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val historyItems: List<String> = emptyList(),
    val articles: List<Article> = emptyList(),
    val articlesPaged: Flow<PagingData<Article>>? = null,
    val dialogData: DialogData? = null,
)
