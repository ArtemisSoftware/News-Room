package com.artemissoftware.feature.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    object SearchNews : SearchEvent()
    object CloseDialog : SearchEvent()
    data class ActivateSearch(val isActive: Boolean) : SearchEvent()
}
