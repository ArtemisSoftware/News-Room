package com.artemissoftware.newsroom.core.network

import com.artemissoftware.newsroom.core.network.dto.ErrorDto
import com.artemissoftware.newsroom.core.network.exceptions.NewsNetworkException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException

internal object HandleApi_2 {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {
            callFunction.invoke()
        } catch (ex: Exception) {
            when (ex) {
                is HttpException -> {
                    convertErrorBody(ex)?.let { error ->
                        throw NewsNetworkException(
                            code = ex.code(),
                            description = error.message,
                        )
                    } ?: run {
                        throw ex
                    }
                }
                else -> {
                    throw ex
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
