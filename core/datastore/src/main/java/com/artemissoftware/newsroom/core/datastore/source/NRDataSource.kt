package com.artemissoftware.newsroom.core.datastore.source

import android.content.Context
import com.artemissoftware.newsroom.core.datastore.models.AppSettingsStore
import com.artemissoftware.newsroom.core.datastore.util.extensions.appSettingsStore
import kotlinx.coroutines.flow.Flow

class NRDataSource /*@Inject*/ constructor(
    private val context: Context,
) {
    fun getAppSettings(): Flow<AppSettingsStore> {
        return context.appSettingsStore.data
    }

    suspend fun setOnboarding(done: Boolean) {
        context.appSettingsStore.updateData {
            it.copy(onboardingDone = done)
        }
    }
}
