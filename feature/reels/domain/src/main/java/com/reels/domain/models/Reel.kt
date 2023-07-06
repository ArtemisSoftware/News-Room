package com.reels.domain.models

data class Reel(
    val type: ReelType,
    val video: ReelVideo,
    val description: String? = null
)
