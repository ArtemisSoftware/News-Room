package com.core.data.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer

class ApiKeyInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        // Read the original request body
        val requestBody: RequestBody? = originalRequest.body
        val buffer = Buffer()
        requestBody?.writeTo(buffer)
        val originalRequestBody: String = buffer.readUtf8()

        // Modify the request body as desired
        val modifiedRequestBody: String = addValueToRequestBody(originalRequestBody)

        // Create a new request with the modified request body
        val modifiedRequest: Request = originalRequest.newBuilder()
            .method(originalRequest.method, RequestBody.create(requestBody?.contentType(), modifiedRequestBody))
            .build()

        return chain.proceed(modifiedRequest)
    }

    private fun addValueToRequestBody(originalRequestBody: String): String {
        // Add the additional value to the request body
        val modifiedRequestBody: String = "$originalRequestBody\n"
        return modifiedRequestBody
    }
}