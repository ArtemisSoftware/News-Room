package com.news.presentation.news

sealed class NewsEvents {

    object openArticle : NewsEvents()
    data class UpdateScrollValue(val value: Int) : NewsEvents()
    data class UpdateMaxScrollingValue(val value: Int) : NewsEvents()
}
