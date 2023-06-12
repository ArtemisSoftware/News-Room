package com.news.domain.models

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val title: String,
    val urlToImage: String,
) {
    companion object {
        val mockArticle = Article(
            author = "Author",
            content = "Content",
            description = "description",
            title = "title",
            urlToImage = "url",
        )
    }
}
