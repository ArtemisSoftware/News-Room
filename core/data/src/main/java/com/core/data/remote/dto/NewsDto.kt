package com.core.data.remote.dto

import com.squareup.moshi.Json

data class NewsDto(
    @field:Json(name = "articles")
    val articles: List<ArticleDto>,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "totalResults")
    val totalResults: Int,
)
