package com.news.presentation.news

//import com.core.domain.models.Article
import com.news.presentation.news.models.Topic

data class NewsState(
//    val articles: List<Article> = emptyList(),
    val topics: List<Topic> = emptyList(),

    val newsTicker: String = "",
    val isLoading: Boolean = false,
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0,
) {
//    fun getTitle() = if (articles.size > 10) {
//        articles
//            .slice(IntRange(start = 0, endInclusive = 9))
//            .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
//    } else {
//        ""
//    }
// TODO: rever isto quando houver paging
//    if (state.articles.itemCount > 10) {
//        state.articles.itemSnapshotList.items
//            .slice(IntRange(start = 0, endInclusive = 9))
//            .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
//    } else {
//        ""
//    }
}
