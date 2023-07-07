package com.reels.domain.models

enum class ReelType(val description: String) {

    LIFESTYLE(description = "Lifestyle"),
    OTHER(description = "Other");
    companion object {
        fun getType(description: String) : ReelType{
            return values().find { it.description ==  description} ?: OTHER
        }
    }
}
