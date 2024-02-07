package com.artemissoftware.newsroom.core.datastore.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettingsStore(
    val onboardingDone: Boolean = false
)
