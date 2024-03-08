package com.core.ui.uievents

import com.core.ui.composables.UiText

sealed class UiEvent {

    object Navigate : UiEvent()

    data class ShowToast(val uiText: UiText, val duration: Int) : UiEvent()
    data class OpenWebBrowser(val url: String) : UiEvent()
    data class Share(val url: String) : UiEvent()
}
