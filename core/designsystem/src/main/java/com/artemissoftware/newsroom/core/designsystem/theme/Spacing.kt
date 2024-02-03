package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val spacing0_5: Dp,
    val spacing1: Dp,
    val spacing1_5: Dp,
    val spacing2: Dp,
)

internal val spacing = Spacing(
    spacing0_5 = 4.dp,
    spacing1 = 8.dp,
    spacing1_5 = 12.dp,
    spacing2 = 16.dp,
)

internal val localSpacing = staticCompositionLocalOf<Spacing> { throw IllegalStateException("No theme installed") }