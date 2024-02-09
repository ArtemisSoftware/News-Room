package com.news.presentation.news.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.domain.models.Article

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    /*
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
    */
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
//    com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme {
//        ArticleCard(
//            article = MockData.article,
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {},
//        )
//    }
}
