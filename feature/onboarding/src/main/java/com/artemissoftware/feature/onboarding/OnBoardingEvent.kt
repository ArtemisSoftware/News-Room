package com.artemissoftware.feature.onboarding

sealed class OnBoardingEvent {
    object GoToPreviewsPage : OnBoardingEvent()
    object GoToNextPage : OnBoardingEvent()
}
