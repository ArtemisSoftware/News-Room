package com.news.data.repositories

import com.core.data.mappers.toListArticles
import com.artemissoftware.newsroom.core.network.NewsApi
import com.core.domain.models.Article
import com.news.domain.repositories.NewsRepository

class NewsRepositoryImpl constructor(
    private val newsApi: com.artemissoftware.newsroom.core.network.NewsApi,
) : NewsRepository {

    override suspend fun getNews(sources: List<String>): List<Article> {
        return try {
            val result = newsApi.getNews(sources = sources.joinToString(separator = ","), page = 1)
            result.toListArticles()
        } catch (e: Exception) {
            emptyList()
        }
    }

//    override suspend fun searchArticles(query: String): List<Article> {
//        return emptyList()//--newsApi.searchArticles(query = query).articles.map { it.toArticle() }
//    }
//
//    override fun getFeaturedArticles(): Flow<List<Article>> {
//        return newsDao.getNewsArticles().map { result -> result.map { it.toArticle() } }
//    }
}
