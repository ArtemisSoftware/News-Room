package com.news.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceDto(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "name")
    val name: String,
)
