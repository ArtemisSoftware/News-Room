package com.news.presentation.news

sealed class NewsEvents {

    data class searchArticles(val query: String) : NewsEvents()

    object openArticle : NewsEvents()
}
