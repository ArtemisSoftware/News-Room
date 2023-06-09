package com.news.data.repositories

import com.core.data.database.dao.NewsDao
import com.news.data.mappers.toArticle
import com.news.data.mappers.toEntity
import com.news.data.remote.NewsApi
import com.news.domain.models.Article
import com.news.domain.repositories.NewsRepository

class NewsRepositoryImpl constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) : NewsRepository {

    override suspend fun getNewsArticle() {
        return try {
            val result = newsApi.getNewsArticles(country = "us", category = "war")
            newsDao.insert(result.articles.map { it.toEntity() })
        } catch (e: Exception) {
        }
    }

    override suspend fun searchArticles(map: MutableMap<String, String>): List<Article> {
        return newsApi.searchArticles(map).articles.map { it.toArticle() }
    }
}
