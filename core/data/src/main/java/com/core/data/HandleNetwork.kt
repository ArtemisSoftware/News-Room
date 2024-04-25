package com.core.data

import com.artemissoftware.newsroom.core.common.exceptions.NewsRoomException
import com.core.domain.DataResponse
import com.core.domain.error.DataError
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

internal object HandleNetwork {

    inline fun <T> safeNetworkCall(apiCall: () -> T): DataResponse<T> {
        return try {
            DataResponse.Success(data = apiCall())
        } catch (ex: Exception) {
            DataResponse.Failure(error = handleException(ex))
        }
    }

    fun handleException(ex: Exception): DataError {
        return when (ex) {
            is NewsRoomException -> {
                DataError.NetworkError.Error(message = ex.description)
            }
            is UnknownHostException -> {
                DataError.NetworkError.Unknown
            }

            is ConnectException -> {
                DataError.NetworkError.Connect
            }

            is SocketTimeoutException -> {
                DataError.NetworkError.SocketTimeout
            }
            is CancellationException -> {
                throw ex
            }
            else -> DataError.NetworkError.Unknown
        }
    }
}
