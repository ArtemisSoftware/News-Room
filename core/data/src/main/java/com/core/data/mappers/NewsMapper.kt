package com.core.data.mappers

import com.core.data.database.entities.ArticleEntity
import com.core.data.remote.dto.ArticleDto
import com.core.domain.models.Article
import com.core.domain.models.Source

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = publishedAt,
        source = Source(this.source.name),
        title = this.title,
        url = url,
        urlToImage = this.urlToImage,
    )
}

fun ArticleDto.toArticle(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = publishedAt,
        source = Source(this.source.name),
        title = this.title,
        url = url,
        urlToImage = this.urlToImage,
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = publishedAt,
        source = this.source,
        title = this.title,
        url = url,
        urlToImage = this.urlToImage,
    )
}