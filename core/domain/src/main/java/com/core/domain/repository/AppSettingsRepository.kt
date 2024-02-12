package com.core.domain.repository

import com.artemissoftware.newsroom.core.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {

    fun getAppSettings(): Flow<AppSettings>

    suspend fun setOnboarding(done: Boolean)
}
