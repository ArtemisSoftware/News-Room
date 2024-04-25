package com.core.domain

import com.core.domain.error.Error

class PaginationException(
    val error: Error,
) : RuntimeException()
