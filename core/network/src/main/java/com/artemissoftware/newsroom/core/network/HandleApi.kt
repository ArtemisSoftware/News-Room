package com.artemissoftware.newsroom.core.network

import com.artemissoftware.newsroom.core.network.dto.ErrorDto
import com.artemissoftware.newsroom.core.network.exceptions.NewsNetworkException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.util.concurrent.CancellationException

internal object HandleApi {

    //    inline fun <T> safeApiCall(apiCall: () -> T): com.core.ui.DataResponse<T> {
//        return try {
//            com.core.ui.DataResponse.Success(data = apiCall())
//        } catch (e: Exception) {
//            com.core.ui.DataResponse.Failure(exception = e)
//        }
//    }

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {
            callFunction.invoke()
        } catch (ex: Exception) {
            when (ex) {
                is CancellationException -> {
                    throw ex
                }

                is HttpException -> {
                    convertErrorBody(ex)?.let { error ->
                        throw NewsNetworkException(
                            code = ex.code(),
                            description = error.message,
                        )
                    } ?: run {
                        throw NewsNetworkException(code = ex.code(), description = ex.message())
                    }
                }
                // TODO : terminar isto
//                is UnknownHostException -> {
//                    throw TaskyNetworkException(TaskyNetworkError.UnknownHost)
//                }
//
//                is ConnectException -> {
//                    throw TaskyNetworkException(TaskyNetworkError.GenericApiError)
//                }
//
//                is SocketTimeoutException -> {
//                    throw TaskyNetworkException(TaskyNetworkError.GenericApiError)
//                }

                else -> {
                    throw NewsNetworkException()
                }
            }
        }
    }

    private fun convertErrorBody(httpException: HttpException): ErrorDto? {
        return try {
            httpException.response()?.errorBody()?.let {
                val moshi: Moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<ErrorDto> = moshi.adapter(ErrorDto::class.java)
                adapter.fromJson(it.string())
            } ?: run {
                ErrorDto(
                    status = "error",
                    code = "ApiComError",
                    message = "Api communication error",
                )
            }
        } catch (exception: Exception) {
            null
        }
    }
}
