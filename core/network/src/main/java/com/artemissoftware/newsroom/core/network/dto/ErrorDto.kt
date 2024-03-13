package com.artemissoftware.newsroom.core.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorDto(
    @Json(name = "code")
    val code: String = "",
    @Json(name = "message")
    val message: String = "",
    @Json(name = "status")
    val status: String = ""
)