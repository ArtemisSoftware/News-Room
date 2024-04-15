package com.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.newsroom.core.common.DataResponse
import com.artemissoftware.newsroom.core.database.dao.NewsDao
import com.artemissoftware.newsroom.core.database.entities.ArticleEntity
import com.artemissoftware.newsroom.core.model.Article
import com.artemissoftware.newsroom.core.network.dto.ArticleDto
import com.artemissoftware.newsroom.core.network.source.NewsApiSource
import com.core.data.HandleNetwork
import com.core.data.mappers.toArticle
import com.core.data.mappers.toEntity
import com.core.data.mappers.toListArticles
import com.core.data.pagination.SearchArticlesPagingSource
import com.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApiSource: NewsApiSource,
) : NewsRepository {
    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles().map { articles ->
            articles.map { it.toArticle() }
        }
    }

    override fun getArticlesPaged(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
            pagingSourceFactory = {
                newsDao.getArticlesPaged()
            },
        ).flow
            .map { value: PagingData<ArticleEntity> ->
                value.map { articleEntity -> articleEntity.toArticle() }
            }
    }

    override suspend fun getArticle(id: Int): Article? {
        return newsDao.getArticle(id)?.toArticle()
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article.toEntity())
    }

    override suspend fun save(article: Article) {
        newsDao.upsert(article.toEntity())
    }

    override suspend fun searchArticles(searchQuery: String, sources: List<String>): List<Article> {
        return newsApiSource.search(
            searchQuery = searchQuery,
            sources = sources.joinToString(separator = ","),
            page = 1,
        ).articles.map { it.toArticle() }
    }

    override fun searchPagedArticles(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchArticlesPagingSource(
                    newsApiSource = newsApiSource,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ","),
                )
            },
        ).flow
            .map { value: PagingData<ArticleDto> ->
                value.map { it.toArticle() }
            }
    }

    override suspend fun getNews(sources: List<String>): DataResponse<List<Article>> {
        return HandleNetwork.safeNetworkCall {
            newsApiSource.getNews(sources = sources.joinToString(separator = ","), page = 1).toListArticles()
        }
    }
}
