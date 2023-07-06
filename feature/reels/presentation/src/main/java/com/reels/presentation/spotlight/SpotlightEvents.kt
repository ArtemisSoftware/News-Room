package com.reels.presentation.spotlight

sealed class SpotlightEvents {

    data class ChangeVolume(val mute: Boolean) : SpotlightEvents()
    object AnimateVolumeIcon : SpotlightEvents()
}
