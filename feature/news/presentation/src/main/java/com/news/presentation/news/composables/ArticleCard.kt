package com.news.presentation.news.composables

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
import com.core.domain.models.Article
import com.core.ui.theme.NewsRoomTheme
import com.core.ui.theme.dimension
import com.core.ui.theme.palette
import com.core.ui.theme.spacing
import com.news.presentation.R
import com.news.presentation.news.MockData

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
//    val context = LocalContext.current
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
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.extraSmallPadding)
                .height(MaterialTheme.dimension.articleCardSize),
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = MaterialTheme.palette.TextTitle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.palette.Body,
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallPadding))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.dimension.smallIconSize),
                    tint = MaterialTheme.palette.Body,
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmallPadding))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.palette.Body,
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
            article = MockData.article,
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}
