package com.artemissoftware.feature.onboarding

import androidx.annotation.DrawableRes
import com.artemissoftware.newsroom.core.model.Page
import com.core.ui.composables.UiText

data class OnBoardingState(
    val currentPage: Int = 0,
    @DrawableRes val image: List<Int> = emptyList(),
    val previewsText: UiText = UiText.DynamicString(""),
    val nextText: UiText = UiText.DynamicString(""),
    val pages: List<Page> = emptyList(),
) {
    fun reachedLastPage() = currentPage == pages.size
}
