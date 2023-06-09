package com.core.data.database

import androidx.room.Database
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

    abstract fun getNewsDao(): NewsDao
}
