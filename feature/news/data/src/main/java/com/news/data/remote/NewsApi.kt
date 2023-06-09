package com.news.data.remote

import com.news.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String,
        @Query("category") category: String,
    ): NewsDto

    @GET("everything")
    suspend fun searchArticles(
        @QueryMap map: MutableMap<String, String>,
    ): NewsDto
}
