package com.artemissoftware.newsroom.core.network.exceptions

import com.artemissoftware.newsroom.core.network.NetworkError

data class NewsNetworkException(
    val code: Int = -1,
    val description: String = "Unknown",
    val loo: NetworkError = NetworkError.SocketTimeout
) : RuntimeException()
