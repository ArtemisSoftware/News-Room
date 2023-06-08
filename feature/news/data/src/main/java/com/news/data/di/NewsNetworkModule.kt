package com.news.data.di

import com.news.data.remote.NewsApi
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

//    @Provides
//    fun provideNewsRepository(newsApiService: NewsApiService):NewsRepository{
//        return NewsRepoImpl(newsApiService)
//    }
}
