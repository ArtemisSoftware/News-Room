package com.artemissoftware.newsroom.core.datastore.source

import android.content.Context
import com.artemissoftware.newsroom.core.datastore.models.AppSettingsStore
import com.artemissoftware.newsroom.core.datastore.util.extensions.appSettingsStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NRDataSource @Inject constructor(
    @ApplicationContext private val context: Context
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
