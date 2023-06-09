package com.core.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    val author: String? = null,
    val content: String,
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val urlToImage: String? = null,
)
