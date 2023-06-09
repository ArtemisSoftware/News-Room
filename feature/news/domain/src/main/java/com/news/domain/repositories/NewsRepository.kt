package com.news.domain.repositories

import com.news.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsArticle()

    suspend fun searchArticles(map: MutableMap<String, String>): List<Article>

    fun getFeaturedArticles(): Flow<List<Article>>
}
