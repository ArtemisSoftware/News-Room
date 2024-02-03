package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews

internal fun Palette.toScheme() = ColorScheme(
    background = background,
    onBackground = onBackground,
    primary = primary,
    onPrimary = onPrimary,
    error = error,
    onError = Color.White,
    surface = surface,
    onSurface = onSurface,

    primaryContainer = background,
    onPrimaryContainer = onBackground,

    secondary = background,
    onSecondary = onBackground,

    secondaryContainer = background,
    onSecondaryContainer = onBackground,

    tertiary = background,
    onTertiary = onBackground,

    tertiaryContainer = background,
    onTertiaryContainer = onBackground,

    errorContainer = background,
    onErrorContainer = onBackground,

    surfaceVariant = background,
    onSurfaceVariant = onBackground,

    inverseSurface = background,
    inverseOnSurface = onBackground,

    outline = onBackground,
    inversePrimary = onBackground,
    outlineVariant = onBackground,
    scrim = onBackground,
    surfaceTint = onBackground,
)

@ThemePreviews
@Composable
private fun ColorSchemePreview() {
    NewsRoomTheme {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1_5),
        ) {
            item {
                ColorSchemeDescription(
                    text = "background/onBackground",
                    background = MaterialTheme.palette.background,
                    foreground = MaterialTheme.palette.onBackground,
                )
            }
            item {
                ColorSchemeDescription(
                    text = "primary/onPrimary",
                    background = MaterialTheme.palette.primary,
                    foreground = MaterialTheme.palette.onPrimary,
                )
            }

            item {
                ColorSchemeDescription(
                    text = "error/onError",
                    background = MaterialTheme.palette.error,
                    foreground = Color.Black,
                )
            }

            item {
                ColorSchemeDescription(
                    text = "surface/onSurface",
                    background = MaterialTheme.palette.surface,
                    foreground = MaterialTheme.palette.onSurface,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun ExtraColorsPreview() {
    NewsRoomTheme {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1_5),
        ) {
            item {
                ColorSchemeDescription(
                    text = "OverlayWhite",
                    background = MaterialTheme.palette.extraColors.OverlayWhite,
                    foreground = Color.Black,
                )
            }
        }
    }
}

@Composable
private fun ColorSchemeDescription(
    text: String,
    background: Color,
    foreground: Color,
) {
    Text(
        text = text,
        modifier = Modifier
            .background(background)
            .height(80.dp)
            .fillMaxWidth()
            .wrapContentSize()
            .padding(all = 16.dp),
        color = foreground,
    )
}
