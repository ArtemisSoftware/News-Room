package com.news.domain.repositories

import com.news.domain.models.Article

interface NewsRepository {

    suspend fun getNewsArticle()

    suspend fun searchArticles(map: MutableMap<String, String>): List<Article>
}
