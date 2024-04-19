package com.artemissoftware.newsroom.core.network.dto

import com.squareup.moshi.Json

data class ErrorDto(
    @field:Json(name = "code")
    val code: String = "",
    @field:Json(name = "message")
    val message: String = "",
    @field:Json(name = "status")
    val status: String = "",
)
