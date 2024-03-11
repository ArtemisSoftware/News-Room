package com.artemissoftware.newsroom.core.network

import com.artemissoftware.newsroom.core.common.DataResponse

object HandleApi {

    inline fun <T> safeApiCall(apiCall: () -> T): DataResponse<T> {
        return try {
            DataResponse.Success(data = apiCall())
        } catch (e: Exception) {
            DataResponse.Failure(exception = e)
        }
    }
}
