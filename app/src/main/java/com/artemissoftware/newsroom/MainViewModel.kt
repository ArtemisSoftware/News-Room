package com.artemissoftware.newsroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.feature.navigation.ONBOARDING_ROUTE
import com.artemissoftware.newsroom.navigation.HOME_ROUTE
import com.core.domain.usecases.GetAppSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppSettingsUseCase: GetAppSettingsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        getStartRoute()
    }

    private fun getStartRoute() {
        viewModelScope.launch {
            getAppSettingsUseCase().collect { appSettings ->

                if (appSettings.onboardingDone) {
                    _state.update { it.copy(showSplash = false, startRoute = HOME_ROUTE) }
                } else {
                    _state.update { it.copy(showSplash = false, startRoute = ONBOARDING_ROUTE) }
                }
            }
            delay(300)
        }
    }
}
