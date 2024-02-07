package com.artemissoftware.newsroom.core.datastore.util.extensions

import android.content.Context
import androidx.datastore.dataStore
import com.artemissoftware.newsroom.core.datastore.serializer.AppSettingsStoreSerializer

val Context.appSettingsStore by dataStore(
    fileName = "app-settings-store.json",
    serializer = AppSettingsStoreSerializer(),
)
