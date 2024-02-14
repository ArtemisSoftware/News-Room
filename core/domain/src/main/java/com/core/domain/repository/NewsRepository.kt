package com.core.domain.repository

import com.artemissoftware.newsroom.core.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticles(): Flow<List<Article>>
}
