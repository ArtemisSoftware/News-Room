package com.reels.data.repositories

import com.reels.data.DummyData
import com.reels.data.mappers.toReel
import com.reels.domain.models.Reel
import com.reels.domain.repositories.ReelsRepository

class ReelsRepositoryImpl() : ReelsRepository {

    override suspend fun getReels(): List<Reel> {
        return DummyData.reelsDto.map { it.toReel() }
    }
}
