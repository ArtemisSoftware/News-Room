package com.artemissoftware.feature.news

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.newsroom.core.model.Article
import com.core.presentation.util.constants.PresentationConstants.PAGINATION
import com.core.ui.composables.DialogData
import kotlinx.coroutines.flow.Flow

internal data class NewsState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val articlesPaged: Flow<PagingData<Article>>? = null,
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0,
    val dialogData: DialogData? = null,
) {
    @Composable
    fun getTitles(): String {
        return if (PAGINATION) {
            val pagedArticles = articlesPaged?.collectAsLazyPagingItems()
            pagedArticles?.let {
                if (pagedArticles.itemCount > 10) {
                    pagedArticles.itemSnapshotList.items
                        .slice(IntRange(start = 0, endInclusive = 9))
                        .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
                } else {
                    ""
                }
            } ?: run {
                ""
            }
        } else {
            if (articles.size > 10) {
                articles
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }
}
