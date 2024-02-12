package com.artemissoftware.feature.onboarding

import com.artemissoftware.newsroom.core.model.OnboardingType
import com.artemissoftware.newsroom.core.model.Page
import com.core.ui.composables.UiText

internal object PreviewData {

    val pages = listOf(
        Page(
            onboardingType = OnboardingType.WORLD,
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        ),
        Page(
            onboardingType = OnboardingType.WORLD,
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        ),
        Page(
            onboardingType = OnboardingType.WORLD,
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        ),
    )

    val onBoardingState = OnBoardingState(
        previewsText = UiText.DynamicString(""),
        nextText = UiText.DynamicString("Next"),
        currentPage = 0,
        pages = pages,
    )
}
