package com.core.data.di

import com.core.data.interceptors.ApiKeyInterceptor
import com.core.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        return OkHttpClient.Builder()
//            .addInterceptor(ApiKeyInterceptor())
//            .addInterceptor(logging)
//            .readTimeout(15L, TimeUnit.SECONDS)
//            .connectTimeout(15L, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//    }
}
