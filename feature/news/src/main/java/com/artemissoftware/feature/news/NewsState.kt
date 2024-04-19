package com.artemissoftware.feature.news

import com.artemissoftware.newsroom.core.model.Article
import com.core.ui.composables.DialogData

internal data class NewsState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val newsTicker: String = "",
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0,
    val dialogData: DialogData? = null,
) {

    fun getTitles() = if (articles.size > 10) {
        articles
            .slice(IntRange(start = 0, endInclusive = 9))
            .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
    } else {
        ""
    }

    //            if (articles.itemCount > 10) {
//                articles.itemSnapshotList.items
//                    .slice(IntRange(start = 0, endInclusive = 9))
//                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
//            } else {
//                ""
//            }
}
