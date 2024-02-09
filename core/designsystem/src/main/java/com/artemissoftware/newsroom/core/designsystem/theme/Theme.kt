package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun NewsRoomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    CompositionLocalProvider(
        localSpacing provides spacing,
        localShape provides shape,
        localDimension provides dimension,
        localPalette provides if (darkTheme) paletteDark else paletteLight,
    ) {
        val colorScheme = localPalette.current.toScheme()

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}

val MaterialTheme.spacing: Spacing
    @Composable
    get() = localSpacing.current

val MaterialTheme.shape: Shaping
    @Composable
    get() = localShape.current

val MaterialTheme.palette: Palette
    @Composable
    get() = localPalette.current
