package com.artemissoftware.feature.news

sealed class NewsEvent {
    data class UpdateScrollValue(val newValue: Int) : NewsEvent()
    data class UpdateMaxScrollingValue(val newValue: Int) : NewsEvent()
}
