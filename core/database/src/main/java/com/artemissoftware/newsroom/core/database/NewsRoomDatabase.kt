package com.artemissoftware.newsroom.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.artemissoftware.newsroom.core.database.dao.NewsDao
import com.artemissoftware.newsroom.core.database.entities.ArticleEntity
import com.artemissoftware.newsroom.core.database.typeconvertor.SourceTypeConvertor

@Database(
    entities = [
        ArticleEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(SourceTypeConvertor::class)
abstract class NewsRoomDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}
