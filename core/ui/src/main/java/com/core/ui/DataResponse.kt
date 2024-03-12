package com.core.ui

sealed interface DataResponse<T> {
    data class Success<T>(val data: T) : DataResponse<T>
    data class Failure<T>(val exception: Exception) : DataResponse<T>

    fun onSuccess(block: (T) -> Unit): DataResponse<T> {
        if (this is Success) block(data)
        return this
    }

    fun <R> mapSuccess(transform: (T) -> R): DataResponse<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Failure -> Failure(exception)
        }
    }

    fun onFailure(block: (Exception) -> Unit): DataResponse<T> {
        if (this is Failure) block(exception)
        return this
    }
}
