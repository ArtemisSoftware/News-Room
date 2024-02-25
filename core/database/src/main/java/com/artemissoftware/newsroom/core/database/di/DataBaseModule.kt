package com.artemissoftware.newsroom.core.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.newsroom.core.database.NewsRoomDatabase
import com.artemissoftware.newsroom.core.database.typeconvertor.SourceTypeConvertor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideNewsRoomDataBase(
        @ApplicationContext context: Context,
    ): NewsRoomDatabase {
        return Room
            .databaseBuilder(
                context,
                NewsRoomDatabase::class.java,
                "news_room_db",
            )
            .addTypeConverter(SourceTypeConvertor())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsRoomDatabase) = database.getNewsDao()
}
