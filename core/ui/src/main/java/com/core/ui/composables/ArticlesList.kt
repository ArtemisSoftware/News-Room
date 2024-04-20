package com.core.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.artemissoftware.newsroom.core.model.Article

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit,
) {
    if (articles.isEmpty()) {
        EmptyScreen("Ainda não definido")
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing3),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1),
    ) {
        items(articles) { article ->
            ArticleCard(
                article = article,
                onClick = { onClick(article) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing3),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1),
    ) {
        items(
            count = articles.itemCount,
            key = articles.itemKey { it.url },
            contentType = articles.itemContentType { "Article" },
        ) {
            articles[it]?.let { article ->
                ArticleCard(
                    article = article,
                    onClick = { onClick(article) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesListPreview() {
    NewsRoomTheme {
        ArticlesList(
            articles = PreviewData.articleList,
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesListEmptyPreview() {
    NewsRoomTheme {
        ArticlesList(
            articles = emptyList(),
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}
