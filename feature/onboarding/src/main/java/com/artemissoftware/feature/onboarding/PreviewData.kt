package com.artemissoftware.feature.onboarding

import com.artemissoftware.newsroom.core.model.Page

internal object PreviewData {

    val pages = listOf(
        Page(
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        ),
        Page(
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        ),
        Page(
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        ),
    )

    val onBoardingState = OnBoardingState(
        previewsText = "",
        nextText = "Next",
        currentPage = 0,
        pages = pages,
    )
}
