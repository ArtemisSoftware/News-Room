package com.reels.domain.repositories

import com.reels.domain.models.ReelVideo

interface ReelsRepository {

    suspend fun getReels(): List<ReelVideo>
}
