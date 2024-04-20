package com.core.presentation.util

import com.core.domain.NetworkError
import com.core.ui.composables.UiText

fun NetworkError.asUiText(): UiText {
    return when (this) {
        NetworkError.Connect -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
        is NetworkError.Error -> UiText.DynamicString(this.message)
        NetworkError.SocketTimeout -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
        NetworkError.Unknown -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
        NetworkError.UnknownHost -> UiText.StringResource(
            com.core.ui.R.string.next,
        )
    }
}
