package com.reels.domain.repositories

import com.reels.domain.models.Reel
import com.reels.domain.models.ReelVideo

interface ReelsRepository {

    suspend fun getReels(): List<Reel>
}
