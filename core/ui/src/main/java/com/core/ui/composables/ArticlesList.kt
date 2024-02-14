package com.core.ui.composables

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
//            EmptyScreen()
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
                modifier = Modifier.fillMaxSize(),
            )
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
