package com.artemissoftware.feature.bookmark

import androidx.paging.PagingData
import com.artemissoftware.newsroom.core.model.Article
import kotlinx.coroutines.flow.Flow

data class BookmarkState(
    val articles: List<Article> = emptyList(),
    val articlesPaged: Flow<PagingData<Article>>? = null,
)
