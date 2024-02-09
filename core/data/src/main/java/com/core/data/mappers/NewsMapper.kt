package com.core.data.mappers

import com.artemissoftware.newsroom.core.datastore.models.AppSettingsStore
import com.artemissoftware.newsroom.core.model.AppSettings
import com.core.data.database.entities.ArticleEntity
import com.core.domain.models.Article
import com.core.domain.models.Source

internal fun AppSettingsStore.toAppSettings() = AppSettings(
    onboardingDone = onboardingDone,
)

/*
fun com.artemissoftware.newsroom.core.network.dto.NewsDto.toListArticles(): List<Article> {
    return this.articles.map { it.toArticle() }
}

fun com.artemissoftware.newsroom.core.network.dto.ArticleDto.toEntity(): ArticleEntity {
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

fun com.artemissoftware.newsroom.core.network.dto.ArticleDto.toArticle(): Article {
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
*/