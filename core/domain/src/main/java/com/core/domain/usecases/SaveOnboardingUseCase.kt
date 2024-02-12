package com.core.domain.usecases

import com.core.domain.repository.AppSettingsRepository
import javax.inject.Inject

class SaveOnboardingUseCase @Inject constructor(private val appSettingsRepository: AppSettingsRepository) {

    suspend operator fun invoke() = appSettingsRepository.setOnboarding(done = true)
}
