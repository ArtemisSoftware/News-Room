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
    val extraColors: ExtraColors,

)

data class ExtraColors(
    val OverlayWhite: Color,
    val BlueGray: Color,
    val textMedium: Color,
    val textTitle: Color,
    val displaySmall: Color,
    val body: Color,
    val bookmarked: Color,
    val placeHolder: Color,
    val shimmer: Color,
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
    bookmarked = Blue,
    placeHolder = Placeholder,
    shimmer = Shimmer,
)

internal val paletteLight = Palette(
    background = White,
    onBackground = Black,
    primary = Blue,
    onPrimary = White,
    error = LightRed,
    surface = White,
    onSurface = Black,
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
    extraColors = extraColors,
)
