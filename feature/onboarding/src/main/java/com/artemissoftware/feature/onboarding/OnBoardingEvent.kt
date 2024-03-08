package com.artemissoftware.feature.onboarding

sealed class OnBoardingEvent {

    data class SwipePage(val index: Int) : OnBoardingEvent()

    object Finish : OnBoardingEvent()
}
