package com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TopLevelDestination_(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val route: String,
)
