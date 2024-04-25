package com.core.presentation.util

import com.core.domain.error.DataError
import com.core.domain.error.Error
import com.core.ui.composables.UiText

fun Error.toUiText(): UiText {
    return when (this) {
        is DataError.NetworkError -> {
            this.asUiText()
        }
        else -> UiText.DynamicString("Error not mapped")
    }
}

private fun DataError.NetworkError.asUiText(): UiText {
    return when (this) {
        DataError.NetworkError.Connect -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
        is DataError.NetworkError.Error -> UiText.DynamicString(this.message)
        DataError.NetworkError.SocketTimeout -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
        DataError.NetworkError.Unknown -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
        DataError.NetworkError.UnknownHost -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
    }
}
