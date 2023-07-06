package com.reels.domain.usecases

import com.reels.domain.models.ReelType
import com.reels.domain.models.Spotlight
import com.reels.domain.repositories.ReelsRepository

class GetSpotlightUseCase /*@Inject*/ constructor(
    private val reelsRepository: ReelsRepository,
) {

    suspend operator fun invoke(): List<Spotlight> {
        val result = mutableListOf<Spotlight>()
        val reels = reelsRepository.getReels()

        ReelType.values().forEach { type ->

            val list = reels.filter { it.type == type }

            if (list.isNotEmpty()) {
                result.add(
                    Spotlight(
                        title = type.description,
                        reels = list,
                    ),
                )
            }
        }

        return result
    }
}
