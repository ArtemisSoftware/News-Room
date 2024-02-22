package com.core.data.di

import com.artemissoftware.newsroom.core.database.dao.NewsDao
import com.artemissoftware.newsroom.core.datastore.source.NRDataSource
import com.artemissoftware.newsroom.core.network.source.NewsApiSource
import com.core.data.repository.AppSettingsRepositoryImpl
import com.core.data.repository.NewsRepositoryImpl
import com.core.domain.repository.AppSettingsRepository
import com.core.domain.repository.NewsRepository
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

    @Provides
    @Singleton
    fun provideNewsRepository(newsDao: NewsDao, newsApiSource: NewsApiSource): NewsRepository {
        return NewsRepositoryImpl(newsDao = newsDao, newsApiSource = newsApiSource)
    }
}
