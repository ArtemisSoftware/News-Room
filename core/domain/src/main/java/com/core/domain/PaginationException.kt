package com.core.domain

class PaginationException(
    val networkError: NetworkError,
) : RuntimeException()
