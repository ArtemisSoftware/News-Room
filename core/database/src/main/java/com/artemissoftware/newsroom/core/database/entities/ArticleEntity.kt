package com.artemissoftware.newsroom.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.artemissoftware.newsroom.core.model.Source

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String? = null,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String? = null,
)
