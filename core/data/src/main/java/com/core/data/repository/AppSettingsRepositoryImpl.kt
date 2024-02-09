package com.core.data.repository

import com.artemissoftware.newsroom.core.datastore.source.NRDataSource
import com.artemissoftware.newsroom.core.model.AppSettings
import com.core.data.mappers.toAppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val nrDataSource: NRDataSource
): AppSettingsRepository {
    override fun getAppSettings(): Flow<AppSettings> {
        return nrDataSource.getAppSettings().map {
            it.toAppSettings()
        }
    }

    override suspend fun setOnboarding(done: Boolean) {
        nrDataSource.setOnboarding(done = done)
    }

}