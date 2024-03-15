package com.core.data

import com.artemissoftware.newsroom.core.common.DataResponse

object HandleNetwork {

    inline fun <T> safeNetworkCall(apiCall: () -> T): DataResponse<T> {
        return try {
            DataResponse.Success(data = apiCall())
        } catch (e: Exception) {
            DataResponse.Failure(exception = e)
        }
    }
}
