package com.news.data.mappers

import com.news.data.remote.dto.ArticleDto
import com.news.domain.models.Article

fun ArticleDto.toArticle(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content,
        description = this.description,
        title = this.title,
        urlToImage = this.urlToImage ?: "",
    )
}
