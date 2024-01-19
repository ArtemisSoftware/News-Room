package com.news.presentation.news.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.domain.models.Article
import com.core.ui.theme.NewsRoomTheme
import com.core.ui.theme.spacing
import com.news.presentation.news.MockData

@Composable
fun ArticlesList(
    articles: List<Article>,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (articles.isEmpty()) {
        // EmptyScreen() // TODO: completar
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.mediumPadding),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.smallPadding),
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

@Preview(showBackground = true)
@Composable
private fun ArticlesListPreview() {
    NewsRoomTheme {
        ArticlesList(
            articles = MockData.articleList,
            modifier = Modifier.fillMaxSize(),
            onClick = {},
        )
    }
}
