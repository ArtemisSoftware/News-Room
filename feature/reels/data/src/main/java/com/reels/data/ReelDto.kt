package com.reels.data

data class ReelDto(
    val type: String,
    val reelUrl: String,
    val isFollowed: Boolean,
    val reelInfo: ReelInfoDto,
)

data class ReelInfoDto(
//    val username: String,
//    val profilePicUrl: String,
//    val description: String? = null,
    val isLiked: Boolean,
//    val likes: Int,
//    val comments: Int,
//    val audio: String = "$username • Original Audio",
//    val audioPicUrl: String = profilePicUrl,
//    val filter: String? = null,
//    val location: String? = null,
//    val taggedPeople: List<String>? = null,
//    val id: String = UUID.randomUUID().toString()
)
