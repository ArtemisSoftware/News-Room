package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val spacing0_5: Dp,
    val spacing1: Dp,
    val spacing1_5: Dp,
    val spacing2: Dp,
    val spacing3: Dp,
    val spacing4: Dp,
)

internal val spacing = Spacing(
    spacing0_5 = 4.dp,
    spacing1 = 8.dp,
    spacing1_5 = 12.dp,
    spacing2 = 16.dp,
    spacing3 = 24.dp,
    spacing4 = 32.dp,
)

internal val localSpacing = staticCompositionLocalOf<Spacing> { throw IllegalStateException("No theme installed") }

//val ExtraSmallPadding = 3.dp
//val ExtraSmallPadding2 = 6.dp
//val MediumPadding1 = 24.dp
//val MediumPadding2 = 30.dp
//val MediumPadding3 = 40.dp
//
//val IndicatorSize = 14.dp
//
//val SmallIconSize = 11.dp
//val IconSize = 20.dp
//
//val ArticleCardSize = 96.dp
//
//val ArticleImageHeight = 248.dp
