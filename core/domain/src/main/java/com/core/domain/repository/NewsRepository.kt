package com.core.domain.repository

import androidx.paging.PagingData
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.DataResponse
import com.core.domain.constants.NewsSources
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun searchArticles(searchQuery: String, sources: List<String> = NewsSources.DEFAULT_SOURCES): DataResponse<List<Article>>

    fun searchPagedArticles(searchQuery: String, sources: List<String> = NewsSources.DEFAULT_SOURCES): Flow<PagingData<Article>>

    fun getArticles(): Flow<List<Article>>

    fun getArticlesPaged(): Flow<PagingData<Article>>

    suspend fun getArticle(id: Int): Article?

    suspend fun deleteArticle(article: Article)

    suspend fun save(article: Article)

    suspend fun getNews(sources: List<String> = NewsSources.DEFAULT_SOURCES): DataResponse<List<Article>>

    suspend fun updateBookmark(article: Article)
}
