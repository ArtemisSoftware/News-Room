package com.news.data.di

import com.artemissoftware.newsroom.core.network.NewsApi
import com.news.data.repositories.NewsRepositoryImpl
import com.news.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: com.artemissoftware.newsroom.core.network.NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi)
    }
}
