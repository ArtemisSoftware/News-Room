package com.artemissoftware.newsroom.core.network.interceptors

import com.artemissoftware.newsroom.core.network.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Modify the request URL to add the API key as a query parameter
        val originalHttpUrl = originalRequest.url

        val newHttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", Constants.API_KEY) // The key and value for the query parameter
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
