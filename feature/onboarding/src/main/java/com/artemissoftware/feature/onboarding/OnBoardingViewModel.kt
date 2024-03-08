package com.artemissoftware.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.model.OnboardingType
import com.core.domain.usecases.GetOnboardingPagesUseCase
import com.core.domain.usecases.SaveOnboardingUseCase
import com.core.ui.composables.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase,
    private val saveOnboardingUseCase: SaveOnboardingUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(OnBoardingState())
    val state: StateFlow<OnBoardingState> = _state.asStateFlow()

    init {
        getOnboarding()
    }

    fun onTriggerEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SwipePage -> swipePage(event.index)
        }
    }
    private fun swipePage(index: Int) = with(_state.value) {
        if (currentPage > index) {
            updatePreviewsPage()
        } else {
            updateNextPage()
        }
    }

    private fun getOnboarding() = with(_state) {
        val pages = getOnboardingPagesUseCase()

        update {
            it.copy(
                pages = pages,
                image = pages.map { it.onboardingType.toImage() },
            )
        }
        updateTexts()
    }

    private fun updateNextPage() = with(_state) {
        if (value.reachedLastPage()) {
            viewModelScope.launch {
                saveOnboardingUseCase()
            }
        } else {
            updateTexts(currentPage = (value.currentPage + 1))
        }
        update {
            it.copy(currentPage = (it.currentPage + 1))
        }
    }

    private fun updatePreviewsPage() = with(_state) {
        update {
            it.copy(currentPage = (it.currentPage - 1))
        }
        updateTexts()
    }

    private fun updateTexts(currentPage: Int = 0) = with(_state) {
        val (previews, next) = when (currentPage) {
            0 -> UiText.DynamicString("") to UiText.StringResource(R.string.next)
            1 -> UiText.StringResource(R.string.back) to UiText.StringResource(R.string.next)
            2 -> UiText.StringResource(R.string.back) to UiText.StringResource(R.string.get_started)
            else -> UiText.DynamicString("") to UiText.DynamicString("")
        }
        update {
            it.copy(
                previewsText = previews,
                nextText = next,
            )
        }
    }

    private fun OnboardingType.toImage(): Int {
        return when (this) {
            OnboardingType.WORLD -> R.drawable.news_1
            OnboardingType.WAR -> R.drawable.news_3
            OnboardingType.FASHION -> R.drawable.news_2
        }
    }
}
