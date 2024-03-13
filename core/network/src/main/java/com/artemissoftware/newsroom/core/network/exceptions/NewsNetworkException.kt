package com.artemissoftware.newsroom.core.network.exceptions

data class NewsNetworkException(
    val code: Int = -1,
    val description: String = "Unknown",
) : RuntimeException()
