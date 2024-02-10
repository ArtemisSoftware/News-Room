package com.artemissoftware.feature.onboarding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension
import com.artemissoftware.newsroom.core.designsystem.theme.palette

@Composable
internal fun PagerIndicator(
    pagesSize: Int,
    selectedPage: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.palette.primary,
    unselectedColor: Color = MaterialTheme.palette.extraColors.BlueGray,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(times = pagesSize) { page ->
            Box(
                modifier = Modifier
                    .size(MaterialTheme.dimension.indicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor),
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PagerIndicatorPreview() {
    NewsRoomTheme {
        PagerIndicator(
            modifier = Modifier.width(80.dp),
            pagesSize = 4,
            selectedPage = 2,
        )
    }
}
