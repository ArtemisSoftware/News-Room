package com.news.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.data.database.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articleentity")
    fun getNewsArticles(): Flow<List<ArticleEntity>>
}
