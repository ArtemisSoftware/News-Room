package com.news.data.mappers

import com.core.data.database.entities.ArticleEntity
import com.core.data.remote.dto.ArticleDto
import com.news.domain.models.Article

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        title = this.title,
        urlToImage = this.urlToImage,
    )
}

fun ArticleDto.toArticle(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content,
        description = this.description,
        title = this.title,
        urlToImage = this.urlToImage ?: "",
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content,
        description = this.description,
        title = this.title,
        urlToImage = this.urlToImage ?: "",
    )
}