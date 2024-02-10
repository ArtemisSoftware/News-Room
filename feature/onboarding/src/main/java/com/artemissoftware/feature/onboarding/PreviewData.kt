package com.artemissoftware.feature.onboarding

import com.artemissoftware.newsroom.core.model.OnboardingType
import com.artemissoftware.newsroom.core.model.Page

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
        previewsText = "",
        nextText = "Next",
        currentPage = 0,
        pages = pages,
    )
}
