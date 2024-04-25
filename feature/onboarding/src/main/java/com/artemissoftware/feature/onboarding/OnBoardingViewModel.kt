package com.artemissoftware.feature.onboarding

import androidx.lifecycle.viewModelScope
import com.artemissoftware.newsroom.core.model.OnboardingType
import com.core.domain.repository.AppSettingsRepository
import com.core.domain.usecases.GetOnboardingPagesUseCase
import com.core.ui.composables.UiText
import com.core.ui.uievents.UiEvent
import com.core.ui.uievents.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.core.presentation.R as CorePresentationR
import com.core.ui.R as CoreUiR

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase,
    private val appSettingsRepository: AppSettingsRepository,
) : UiEventViewModel() {

    private val _state = MutableStateFlow(OnBoardingState())
    val state: StateFlow<OnBoardingState> = _state.asStateFlow()

    init {
        getOnboarding()
    }

    fun onTriggerEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SwipePage -> swipePage(event.index)
            OnBoardingEvent.Finish -> updateNextPage()
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
                appSettingsRepository.setOnboarding(done = true)
                sendUiEvent(UiEvent.Navigate)
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
            0 -> UiText.DynamicString("") to UiText.StringResource(CorePresentationR.string.next)
            1 -> UiText.StringResource(CorePresentationR.string.back) to UiText.StringResource(CorePresentationR.string.next)
            2 -> UiText.StringResource(CorePresentationR.string.back) to UiText.StringResource(CorePresentationR.string.get_started)
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
            OnboardingType.WORLD -> CoreUiR.drawable.news_1
            OnboardingType.WAR -> CoreUiR.drawable.news_3
            OnboardingType.FASHION -> CoreUiR.drawable.news_2
        }
    }
}
