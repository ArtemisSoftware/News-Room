package com.artemissoftware.newsroom.core.network

import com.artemissoftware.newsroom.core.common.exceptions.NewsRoomException
import com.artemissoftware.newsroom.core.network.dto.ErrorDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException

internal object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {
            callFunction.invoke()
        } catch (ex: Exception) {
            when (ex) {
                is HttpException -> {
                    convertErrorBody(ex)?.let { error ->
                        throw NewsRoomException(
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
                null
            }
        } catch (exception: Exception) {
            null
        }
    }
}
