package com.core.domain

import com.core.domain.error.DataError

class PaginationException(
    val networkError: DataError.NetworkError,
) : RuntimeException()
