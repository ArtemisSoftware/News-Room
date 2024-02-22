package com.core.data.mappers

import com.artemissoftware.newsroom.core.database.entities.ArticleEntity
import com.artemissoftware.newsroom.core.datastore.models.AppSettingsStore
import com.artemissoftware.newsroom.core.model.AppSettings
import com.artemissoftware.newsroom.core.model.Article
import com.artemissoftware.newsroom.core.model.Source
import com.artemissoftware.newsroom.core.network.dto.ArticleDto
import com.artemissoftware.newsroom.core.network.dto.NewsDto

internal fun AppSettingsStore.toAppSettings() = AppSettings(
    onboardingDone = onboardingDone,
)

internal fun NewsDto.toListArticles(): List<Article> {
    return this.articles.map { it.toArticle() }
}

internal fun ArticleDto.toArticle(): Article {
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

/*


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
*/



internal fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
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

internal fun ArticleEntity.toArticle(): Article {
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
