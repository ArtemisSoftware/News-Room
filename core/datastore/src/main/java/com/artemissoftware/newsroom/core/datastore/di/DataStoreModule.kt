package com.artemissoftware.newsroom.core.datastore.di

import android.content.Context
import com.artemissoftware.newsroom.core.datastore.source.NRDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideNRDataSource(@ApplicationContext context: Context): NRDataSource {
        return NRDataSource(context)
    }
}