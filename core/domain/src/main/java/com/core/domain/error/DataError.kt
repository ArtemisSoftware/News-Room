package com.core.domain.error

sealed interface DataError : Error {

    sealed class NetworkError : DataError {

        data class Error(val message: String) : NetworkError()

        object SocketTimeout : NetworkError()

        object Unknown : NetworkError()
        object UnknownHost : NetworkError()

        object Connect : NetworkError()
    }
}
