package com.artemissoftware.newsroom.core.network

import com.artemissoftware.newsroom.core.network.Constants.API_KEY
import com.artemissoftware.newsroom.core.network.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsDto
}
