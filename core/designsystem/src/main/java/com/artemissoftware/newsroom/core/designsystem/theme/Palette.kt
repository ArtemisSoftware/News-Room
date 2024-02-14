package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Palette(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val error: Color,
    val surface: Color,
    val onSurface: Color,

//    val Body: Color,
//    val DisplaySmall: Color,

//    val TextTitle: Color,
//    val InputBackground: Color,
//    val Placeholder: Color,
//    val Shimmer: Color,

    val extraColors: ExtraColors,

)

data class ExtraColors(
    val OverlayWhite: Color,
    val BlueGray: Color,
    val textMedium: Color,
    val textTitle: Color,
    val displaySmall: Color,
    val body: Color,
)

internal val localPalette = staticCompositionLocalOf<Palette> { throw IllegalStateException("No theme installed") }

/**
 * Special purpose not overriden in dark mode
 */
internal val extraColors = ExtraColors(
    OverlayWhite = WhiteGray,
    BlueGray = BlueGray,
    textMedium = TextMedium,
    textTitle = TextTitle,
    displaySmall = DisplaySmall,
    body = Body,
)

internal val paletteLight = Palette(
    background = White,
    onBackground = Black,
    primary = Blue,
    onPrimary = White,
    error = LightRed,
    surface = White,
    onSurface = Black,
//    Body = Color(0xFF4E4B66),
//    DisplaySmall = Color(0xFF000000),
//    TextMedium = Color(0xFF4E4B66),
//    TextTitle = Color(0xFF000000),
//    InputBackground = Color(0xFFffffff),
//    Placeholder = Color(0xFFA0A3BD),
//    Shimmer = Color(0xFFC3C3C3),
    extraColors = extraColors,
)

internal val paletteDark = Palette(
    background = Black,
    onBackground = White,
    primary = Blue,
    onPrimary = White,
    error = DarkRed,
    surface = LightBlack,
    onSurface = White,
//    Body = Color(0xFFB0B3B8),
//    DisplaySmall = Color(0xFFFFFFFF),
//    TextMedium = Color(0xFFB0B3B8),
//    TextTitle = Color(0xFFE4E6EB),
//    InputBackground = Color(0xFF3A3B3C),
//    Placeholder = Color(0xFFDDDDDD),
//    Shimmer = Color(0xFF414243),
    extraColors = extraColors,
)
