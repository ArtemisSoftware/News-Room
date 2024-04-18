package com.core.domain

sealed class NetworkError {

    data class Error(val message: String) : NetworkError()

    object SocketTimeout : NetworkError()

    object Unknown : NetworkError()
    object UnknownHost : NetworkError()

    object Connect : NetworkError()
}
