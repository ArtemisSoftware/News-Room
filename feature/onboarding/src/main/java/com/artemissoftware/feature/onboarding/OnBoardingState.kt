package com.artemissoftware.feature.onboarding

import androidx.annotation.DrawableRes
import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.model.Page

data class OnBoardingState(
    val currentPage: Int = 0,
    @DrawableRes val image: Int = R.drawable.news_1,
    val previewsText: String = "",
    val nextText: String = "",
    val pages: List<Page> = emptyList()
){
    fun reachedLastPage() = (currentPage + 1) == pages.size
}
