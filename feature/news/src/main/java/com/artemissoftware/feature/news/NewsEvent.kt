package com.artemissoftware.feature.news

internal sealed class NewsEvent {
    data class UpdateScrollValue(val newValue: Int) : NewsEvent()
    data class UpdateMaxScrollingValue(val newValue: Int) : NewsEvent()

    object CloseDialog : NewsEvent()
}
