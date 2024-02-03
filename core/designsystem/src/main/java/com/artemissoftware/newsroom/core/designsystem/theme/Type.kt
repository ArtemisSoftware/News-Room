package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.newsroom.core.designsystem.R
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews

private val fontFamily: FontFamily = FontFamily(
    listOf(
        Font(R.font.poppins_bold),
        Font(R.font.poppins_regular),
        Font(R.font.poppins_semibold),
    ),
)

private val undefined = TextStyle()

internal val displaySmall = TextStyle(
    fontSize = 24.sp,
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    lineHeight = 36.sp,
)
internal val displayMedium = TextStyle(
    fontSize = 32.sp,
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    lineHeight = 48.sp,
)
internal val bodySmall = TextStyle(
    fontSize = 14.sp,
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    lineHeight = 21.sp,
)
internal val bodyMedium = TextStyle(
    fontSize = 16.sp,
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    lineHeight = 24.sp,
)
internal val labelSmall = TextStyle(
    fontSize = 13.sp,
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    lineHeight = 19.sp,
)

internal val Typography = Typography(
    displayLarge = undefined,
    displayMedium = displayMedium,
    displaySmall = displaySmall,

    headlineLarge = undefined,
    headlineMedium = undefined,
    headlineSmall = undefined,

    titleLarge = undefined,
    titleMedium = undefined,
    titleSmall = undefined,

    bodyLarge = undefined,
    bodyMedium = bodyMedium,
    bodySmall = bodySmall,

    labelLarge = undefined,
    labelMedium = undefined,
    labelSmall = labelSmall,
)

@ThemePreviews
@Composable
private fun TypographyPreview() {
    val typography = mapOf(
        "undefined" to undefined,
        "displayMedium" to displayMedium,
        "displaySmall" to displaySmall,
        "bodyMedium" to bodyMedium,
        "bodySmall" to bodySmall,
        "labelSmall" to labelSmall,
    )

    NewsRoomTheme {
        Surface {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing0_5),
            ) {
                items(typography.toList()) { pair ->
                    TypographyDescription(
                        text = pair.first,
                        textStyle = pair.second,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun TypographyDescription(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$text font ",
        style = textStyle,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}
