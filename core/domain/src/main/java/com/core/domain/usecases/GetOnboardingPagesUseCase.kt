package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.OnboardingType
import com.artemissoftware.newsroom.core.model.Page
import javax.inject.Inject

class GetOnboardingPagesUseCase @Inject constructor() {

    operator fun invoke() = listOf(
        Page(
            onboardingType = OnboardingType.WORLD,
            title = "The world",
            description = "News of an ever changing world",
        ),
        Page(
            onboardingType = OnboardingType.FASHION,
            title = "Follow the fashion",
            description = "Never go out of fashion",
        ),
        Page(
            onboardingType = OnboardingType.WAR,
            title = "In times of war",
            description = "Follow all the wars",
        ),
    )
}
