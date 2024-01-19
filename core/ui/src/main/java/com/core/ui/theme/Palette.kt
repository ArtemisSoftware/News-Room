package com.core.ui.theme

import androidx.compose.ui.graphics.Color

data class Palette(
    val Body: Color,
    val DisplaySmall: Color,
    val TextMedium: Color,
    val TextTitle: Color,
    val InputBackground: Color,
    val Placeholder: Color,
    val Shimmer: Color,
)

val palette = Palette(
    Body = Color(0xFF4E4B66),
    DisplaySmall = Color(0xFF000000),
    TextMedium = Color(0xFF4E4B66),
    TextTitle = Color(0xFF000000),
    InputBackground = Color(0xFF4E4B66),
    Placeholder = Color(0xFFA0A3BD),
    Shimmer = Color(0xFFC3C3C3),
)
