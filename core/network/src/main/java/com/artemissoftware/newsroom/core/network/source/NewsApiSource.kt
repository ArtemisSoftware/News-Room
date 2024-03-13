package com.artemissoftware.newsroom.core.network.source

import com.artemissoftware.newsroom.core.network.HandleApi
import com.artemissoftware.newsroom.core.network.NewsApi
import com.artemissoftware.newsroom.core.network.dto.NewsDto
import javax.inject.Inject

class NewsApiSource @Inject constructor(
    private val newsApi: NewsApi,
) {

    suspend fun getNews(sources: String, page: Int): NewsDto {
        return HandleApi.safeApiCall { newsApi.getNews(sources = sources, page = page) }
    }

    suspend fun search(searchQuery: String, sources: String, page: Int): NewsDto {
        return HandleApi.safeApiCall { newsApi.searchNews(sources = sources, page = page, searchQuery = searchQuery) }
    }
}
