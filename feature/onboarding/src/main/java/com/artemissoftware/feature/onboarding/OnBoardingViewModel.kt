package com.artemissoftware.feature.onboarding

import androidx.lifecycle.ViewModel
import com.core.domain.usecases.GetOnboardingPagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(OnBoardingState())
    val state: StateFlow<OnBoardingState> = _state.asStateFlow()

    init {
        updateOnboarding()
    }

    fun onTriggerEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.GoToNextPage -> updateNextPage()
            OnBoardingEvent.GoToPreviewsPage -> updatePreviewsPage()
        }
    }

    private fun updateOnboarding() = with(_state) {
        update {
            it.copy(pages = getOnboardingPagesUseCase())
        }
        updateTexts()
    }

    private fun updateNextPage() = with(_state) {
        update {
            it.copy(currentPage = (it.currentPage + 1))
        }

        if (value.reachedLastPage()) {
        } else {
            updateTexts()
        }
    }

    private fun updatePreviewsPage() = with(_state) {
        update {
            it.copy(currentPage = (it.currentPage - 1))
        }
        updateTexts()
    }

    private fun updateTexts() = with(_state) {
        val (previews, next) = when (value.currentPage) {
            0 -> "" to "Next"
            1 -> "Back" to "Next"
            2 -> "Back" to "Get Started"
            else -> "" to ""
        }
        update {
            it.copy(
                previewsText = previews,
                nextText = next,
            )
        }
    }
}
