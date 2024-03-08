package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.AppSettings
import com.core.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetAppSettingsUseCase @Inject constructor(private val appSettingsRepository: AppSettingsRepository) {

    suspend operator fun invoke(): AppSettings {
        return appSettingsRepository.getAppSettings().first()
    }
}
