package com.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.data.database.dao.NewsDao
import com.core.data.database.entities.ArticleEntity
import com.core.data.database.typeconvertor.SourceTypeConvertor

@Database(
    entities = [
        ArticleEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(SourceTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}
