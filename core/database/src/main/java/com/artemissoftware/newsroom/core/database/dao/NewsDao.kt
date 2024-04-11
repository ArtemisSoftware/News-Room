package com.artemissoftware.newsroom.core.database.dao

//import androidx.paging.PagingSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artemissoftware.newsroom.core.database.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleEntity: ArticleEntity)

    @Delete
    suspend fun delete(articleEntity: ArticleEntity)

    @Query("SELECT * FROM ArticleEntity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM ArticleEntity WHERE url=:url")
    suspend fun getArticle(url: String): ArticleEntity?

    @Query("SELECT * FROM ArticleEntity WHERE id=:id")
    suspend fun getArticle(id: Int): ArticleEntity?

    @Query("SELECT * FROM ArticleEntity")
    fun getArticlesPaged(): PagingSource<Int, ArticleEntity>
}
