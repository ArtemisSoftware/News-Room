package com.core.domain

sealed interface DataResponse2<T> {
    data class Success<T>(val data: T) : DataResponse2<T>
    data class Failure<T>(val exception: NetworkError) : DataResponse2<T>

    fun onSuccess(block: (T) -> Unit): DataResponse2<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (NetworkError) -> Unit): DataResponse2<T> {
        if (this is Failure) block(exception)
        return this
    }
}
