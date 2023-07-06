package com.reels.domain.models

sealed class ReelVideo {

    data class Remote(val url: String) : ReelVideo()

    data class Local(val file: String) : ReelVideo()
}
