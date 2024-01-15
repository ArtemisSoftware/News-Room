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
        @Query("q") query: String,
        @Query("apiKey") key: String = API_KEY,
    ): NewsDto

    companion object {
        const val API_KEY = "2b3350c2e130493a94f280d8c05ca388"
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}
