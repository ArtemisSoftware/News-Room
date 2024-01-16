package com.news.domain.repositories

import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsArticle(country: String, category: String)

    suspend fun searchArticles(query: String): List<Article>

    fun getFeaturedArticles(): Flow<List<Article>>
}
