package com.artemissoftware.newsroom.core.common.exceptions

data class NewsRoomException(
    val code: Int = -1,
    val description: String = "Unknown",
) : RuntimeException()
