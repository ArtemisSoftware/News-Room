package com.core.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class SourceDto(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "name")
    val name: String,
)
