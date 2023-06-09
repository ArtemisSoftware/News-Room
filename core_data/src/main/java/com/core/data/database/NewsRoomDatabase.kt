package com.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.core.data.database.dao.NewsDao
import com.core.data.database.entities.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class NewsRoomDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): NewsRoomDatabase {
            return Room.databaseBuilder(context, NewsRoomDatabase::class.java, "news_room_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getNewsDao(): NewsDao
}
