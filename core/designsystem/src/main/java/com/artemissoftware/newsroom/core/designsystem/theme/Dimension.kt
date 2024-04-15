package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimension(
    val indicatorSize: Dp,
    val smallIconSize: Dp,
    val iconSize: Dp,
    val articleCardSize: Dp,
    val articleImageHeight: Dp,
    val iconSizeBig: Dp,
)

val dimension = Dimension(
    indicatorSize = 14.dp,
    smallIconSize = 12.dp,
    iconSize = 20.dp,
    articleCardSize = 96.dp,
    articleImageHeight = 248.dp,
    iconSizeBig = 120.dp,
)

internal val localDimension = staticCompositionLocalOf<Dimension> { throw IllegalStateException("No theme installed") }

val MaterialTheme.dimension: Dimension
    @Composable
    get() = localDimension.current
