package com.core.data.di

import android.app.Application
import androidx.room.Room
import com.core.data.database.NewsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsRoomDatabase(app: Application): NewsRoomDatabase {
        return Room.databaseBuilder(
            app,
            NewsRoomDatabase::class.java,
            "news_room_db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
