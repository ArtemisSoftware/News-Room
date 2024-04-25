package com.core.presentation.composables.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.core.ui.util.extensions.shimmerEffect

@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(MaterialTheme.dimension.articleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.spacing0_5)
                .height(MaterialTheme.dimension.articleCardSize),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.spacing.spacing3)
                    .padding(horizontal = MaterialTheme.spacing.spacing0_5)
                    .shimmerEffect(),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = MaterialTheme.spacing.spacing0_5)
                        .height(MaterialTheme.spacing.spacing2)
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Composable
fun ArticleCardListShimmerEffect(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing3),
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = modifier.padding(horizontal = MaterialTheme.spacing.spacing1),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardShimmerEffectPreview() {
    NewsRoomTheme {
        ArticleCardShimmerEffect(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardListShimmerEffectPreview() {
    NewsRoomTheme {
        ArticleCardListShimmerEffect(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
