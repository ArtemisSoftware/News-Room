package com.core.domain

import com.core.domain.error.Error

sealed interface DataResponse<T> {
    data class Success<T>(val data: T) : DataResponse<T>
    data class Failure<T>(val error: Error) : DataResponse<T>

    fun onSuccess(block: (T) -> Unit): DataResponse<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (Error) -> Unit): DataResponse<T> {
        if (this is Failure) block(error)
        return this
    }
}
