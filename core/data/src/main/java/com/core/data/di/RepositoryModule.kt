package com.core.data.di

import com.artemissoftware.newsroom.core.datastore.source.NRDataSource
import com.core.data.repository.AppSettingsRepositoryImpl
import com.core.domain.repository.AppSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppSettingsRepository(nrDataSource: NRDataSource): AppSettingsRepository {
        return AppSettingsRepositoryImpl(nrDataSource)
    }
}
