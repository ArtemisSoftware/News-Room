package com.core.data

import com.artemissoftware.newsroom.core.network.exceptions.NewsNetworkException
import com.core.domain.DataResponse2
import com.core.domain.NetworkError
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

object HandleNetwork2 {

    inline fun <T> safeNetworkCall(apiCall: () -> T): DataResponse2<T> {
        return try {
            DataResponse2.Success(data = apiCall())
        } catch (ex: Exception) {
            val error = when (ex) {
                is NewsNetworkException -> {
                    NetworkError.Error(message = ex.description)
                }
                is UnknownHostException -> {
                    NetworkError.Unknown
                }

                is ConnectException -> {
                    NetworkError.Connect
                }

                is SocketTimeoutException -> {
                    NetworkError.SocketTimeout
                }
                is CancellationException -> {
                    throw ex
                }
                else -> NetworkError.Unknown
            }
            DataResponse2.Failure(exception = error)
        }
    }
}
