package com.artemissoftware.newsroom.core.datastore.source

import android.content.Context
import com.artemissoftware.newsroom.core.datastore.util.extensions.appSettingsStore
import com.artemissoftware.newsroom.core.model.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NRDataSource constructor(
    private val context: Context,
){
    fun getUser(): Flow<AppSettings> {
        return context.appSettingsStore.data.map {
            AppSettings(
                onboardingDone = it.onboardingDone
            )
        }
    }

    suspend fun setOnboarding(done: Boolean) {
        context.appSettingsStore.updateData {
            it.copy(onboardingDone = done)
        }
    }
}