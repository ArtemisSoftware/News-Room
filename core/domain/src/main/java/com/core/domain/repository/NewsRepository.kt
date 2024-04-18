package com.core.domain.repository

import androidx.paging.PagingData
import com.artemissoftware.newsroom.core.common.DataResponse
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.DataResponse2
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticles(): Flow<List<Article>>

    fun getArticlesPaged(): Flow<PagingData<Article>>

    suspend fun getArticle(id: Int): Article?

    suspend fun deleteArticle(article: Article)

    suspend fun save(article: Article)

    suspend fun searchArticles(searchQuery: String, sources: List<String>): List<Article>

    fun searchPagedArticles(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun getNews(sources: List<String>): DataResponse2<List<Article>>
}
