package com.core.data.repository

import com.artemissoftware.newsroom.core.database.dao.NewsDao
import com.artemissoftware.newsroom.core.model.Article
import com.core.data.mappers.toArticle
import com.core.data.mappers.toEntity
import com.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
) : NewsRepository {
    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles().map { articles ->
            articles.map { it.toArticle() }
        }
    }

    override suspend fun getArticle(id: Int): Article? {
        return newsDao.getArticle(id).toArticle()
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article.toEntity())
    }

    override suspend fun save(article: Article) {
        newsDao.upsert(article.toEntity())
    }

    override suspend fun searchArticles(query: String, sources: List<String>): List<Article> {
        return emptyList()//--newsApi.searchArticles(query = query).articles.map { it.toArticle() }
    }
}
