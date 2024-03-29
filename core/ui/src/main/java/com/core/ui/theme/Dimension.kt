package com.core.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimension(
    val indicatorSize: Dp,
    val smallIconSize: Dp,
    val iconSize: Dp,
    val articleCardSize: Dp,
    val articleImageHeight: Dp,
)

val dimension = Dimension(
    indicatorSize = 14.dp,
    smallIconSize = 11.dp,
    iconSize = 20.dp,
    articleCardSize = 96.dp,
    articleImageHeight = 248.dp,
)
