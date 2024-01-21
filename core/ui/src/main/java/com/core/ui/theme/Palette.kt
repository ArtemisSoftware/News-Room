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

val paletteLight = Palette( // TODO: Should be internal
    Body = Color(0xFF4E4B66),
    DisplaySmall = Color(0xFF000000),
    TextMedium = Color(0xFF4E4B66),
    TextTitle = Color(0xFF000000),
    InputBackground = Color(0xFFffffff),
    Placeholder = Color(0xFFA0A3BD),
    Shimmer = Color(0xFFC3C3C3),
)

val paletteDark = Palette(
    Body = Color(0xFFB0B3B8),
    DisplaySmall = Color(0xFFFFFFFF),
    TextMedium = Color(0xFFB0B3B8),
    TextTitle = Color(0xFFE4E6EB),
    InputBackground = Color(0xFF3A3B3C),
    Placeholder = Color(0xFFDDDDDD),
    Shimmer = Color(0xFF414243),
)
