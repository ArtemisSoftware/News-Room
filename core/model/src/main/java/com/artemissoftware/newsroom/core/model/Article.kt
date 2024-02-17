package com.artemissoftware.newsroom.core.model

data class Article(
    val id: Int? = null,
    val author: String? = null,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String? = null
)
