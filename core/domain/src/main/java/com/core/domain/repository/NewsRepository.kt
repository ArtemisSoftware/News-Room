package com.core.domain.repository

import com.artemissoftware.newsroom.core.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(id: Int): Article?

    suspend fun deleteArticle(article: Article)

    suspend fun save(article: Article)

    suspend fun searchArticles(searchQuery: String, sources: List<String>): List<Article>

    suspend fun getNews(sources: List<String>): List<Article>
}
