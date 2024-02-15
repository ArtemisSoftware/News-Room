package com.core.ui.uievents

import com.core.ui.composables.UiText

sealed class UiEvent {

    data class ShowToast(val uiText: UiText, val duration: Int) : UiEvent()
}