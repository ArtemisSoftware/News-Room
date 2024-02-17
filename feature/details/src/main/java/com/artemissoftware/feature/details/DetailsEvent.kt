package com.artemissoftware.feature.details

sealed class DetailsEvent {
    object UpdateBookMark : DetailsEvent()
    object OpenWebContent : DetailsEvent()
    object ShareArticle : DetailsEvent()
}
