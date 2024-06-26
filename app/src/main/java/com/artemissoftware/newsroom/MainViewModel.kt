package com.artemissoftware.newsroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.feature.navigation.ONBOARDING_GRAPH
import com.artemissoftware.newsroom.navigation.HOME_GRAPH
import com.core.domain.repository.AppSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        getStartRoute()
    }

    private fun getStartRoute() {
        viewModelScope.launch {
            val result = appSettingsRepository.getAppSettings().first()

            if (result.onboardingDone) {
                _state.update { it.copy(showSplash = false, startRoute = HOME_GRAPH) }
            } else {
                _state.update { it.copy(showSplash = false, startRoute = ONBOARDING_GRAPH) }
            }

            delay(300)
        }
    }
}
