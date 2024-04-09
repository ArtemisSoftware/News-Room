package com.artemissoftware.newsroom.core.common

sealed class Resource<T>(val data: T? = null, val exception: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(exception: String, data: T? = null) : Resource<T>(data, exception)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
