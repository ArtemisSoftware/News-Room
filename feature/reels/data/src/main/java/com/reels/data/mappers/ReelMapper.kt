package com.reels.data.mappers

import com.reels.data.ReelDto
import com.reels.domain.models.Reel
import com.reels.domain.models.ReelType
import com.reels.domain.models.ReelVideo

fun ReelDto.toReel(): Reel {
    return Reel(
        type = ReelType.getType(description = this.type),
        video = this.toReelVideo(),
    )
}

fun ReelDto.toReelVideo(): ReelVideo {
    return ReelVideo.Remote(url = this.reelUrl)
}
