package com.reels.domain.models

sealed class SpotlightVideo {

    data class Remote(val url: String) : SpotlightVideo()

    data class Local(val file: String) : SpotlightVideo()
}
