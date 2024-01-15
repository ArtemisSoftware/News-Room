package com.news.data.repositories

import com.core.data.database.dao.NewsDao
import com.news.data.mappers.toArticle
import com.news.data.mappers.toEntity
import com.core.data.remote.NewsApi
import com.news.domain.models.Article
import com.news.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) : NewsRepository {

    override suspend fun getNewsArticle(country: String, category: String) {
        return try {
            val result = newsApi.getNewsArticles(country = country, category = category)
            //--newsDao.insert(result.articles.map { it.toEntity() })
        } catch (e: Exception) {
        }
    }

    override suspend fun searchArticles(query: String): List<Article> {
        return emptyList()//--newsApi.searchArticles(query = query).articles.map { it.toArticle() }
    }

    override fun getFeaturedArticles(): Flow<List<Article>> {
        return newsDao.getNewsArticles().map { result -> result.map { it.toArticle() } }
    }
}
