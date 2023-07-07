package com.reels.domain.repositories

import com.reels.domain.models.Reel

interface ReelsRepository {

    suspend fun getReels(): List<Reel>
}
