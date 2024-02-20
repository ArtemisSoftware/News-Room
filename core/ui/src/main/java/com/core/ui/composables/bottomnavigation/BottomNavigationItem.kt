package com.core.ui.composables.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
)
