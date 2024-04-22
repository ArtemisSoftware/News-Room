package com.artemissoftware.newsroom.core.network

import com.artemissoftware.newsroom.core.common.DataError
import com.artemissoftware.newsroom.core.common.DataResponse
import com.artemissoftware.newsroom.core.network.dto.ErrorDto
import com.artemissoftware.newsroom.core.common.exceptions.NewsRoomException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.util.concurrent.CancellationException
import com.artemissoftware.newsroom.core.common.Result

internal object HandleApi {

    inline fun <T> s_afeNetworkCall(apiCall: () -> T): DataResponse<T> {
        return DataResponse.Success(apiCall.invoke())
    }

    suspend fun <T> register(apiCall: suspend () -> T): Result<T, DataError.Network> {
        return Result.Success(apiCall.invoke())
//        // API call logic
//        return try {
//            val user = User("dummy", "dummy", "dummy")
//            Result.Success(user)
//        } catch(e: HttpException) {
//            when(e.code()) {
//                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
//                413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
//                else -> Result.Error(DataError.Network.UNKNOWN)
//            }
//        }
    }

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
                        throw NewsRoomException(
                            code = ex.code(),
                            description = error.message,
                        )
                    } ?: run {
                        throw NewsRoomException(code = ex.code(), description = ex.message())
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
                    throw NewsRoomException()
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
