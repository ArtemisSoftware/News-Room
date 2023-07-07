package com.news.data.di

import com.core.data.database.NewsRoomDatabase
import com.news.data.remote.NewsApi
import com.news.data.repositories.NewsRepositoryImpl
import com.news.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsNetworkModule {

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsRoomDatabase: NewsRoomDatabase): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi, newsDao = newsRoomDatabase.getNewsDao())
    }
}
