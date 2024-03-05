package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.AppSettings
import com.core.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppSettingsUseCase @Inject constructor(private val appSettingsRepository: AppSettingsRepository) {

    operator fun invoke(): Flow<AppSettings> {
        return appSettingsRepository.getAppSettings()
    }
}
