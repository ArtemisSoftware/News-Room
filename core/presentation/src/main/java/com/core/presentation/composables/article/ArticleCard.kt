package com.core.presentation.composables.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.artemissoftware.newsroom.core.model.Article
import com.core.ui.R
import com.core.presentation.composables.PreviewData

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onClick.invoke() },
    ) {
        AsyncImage(
            modifier = Modifier
                .size(MaterialTheme.dimension.articleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(article.urlToImage)
                .error(R.drawable.ic_news)
                .placeholder(R.drawable.ic_news)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.spacing0_5)
                .height(MaterialTheme.dimension.articleCardSize),
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.palette.extraColors.body,
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.spacing1))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.dimension.smallIconSize),
                    tint = MaterialTheme.palette.extraColors.body,
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.spacing0_5))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.palette.extraColors.body,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    NewsRoomTheme {
        ArticleCard(
            article = PreviewData.article,
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}
