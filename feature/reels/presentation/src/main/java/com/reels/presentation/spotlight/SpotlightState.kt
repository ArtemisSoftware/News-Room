package com.reels.presentation.spotlight

data class SpotlightState(
    val isMuted: Boolean = false,
    val showVolumeIcon: Boolean = false,
    val date: String,
    val spotlights: List<SpotlightV2> = emptyList(),
    val expandReels: Boolean = false
)
