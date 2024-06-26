package com.artemissoftware.feature.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.core.presentation.R
import com.core.presentation.composables.article.ArticleList

@Composable
internal fun BookmarkScreen(
    navigateToDetails: (Int) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    BookmarkContent(
        state = state,
        navigateToDetails = navigateToDetails,
    )
}

@Composable
private fun BookmarkContent(
    state: BookmarkState,
    navigateToDetails: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = MaterialTheme.spacing.spacing3)
            .padding(top = MaterialTheme.spacing.spacing3),
    ) {
        Text(
            text = stringResource(id = R.string.bookmark),
            style = MaterialTheme.typography.displayMedium,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing3))

        ArticleList(
            modifier = Modifier
                .fillMaxWidth(),
            articles = state.articles,
            pagedArticles = state.articlesPaged,
            onClick = { article ->
                article.id?.let { navigateToDetails(it) }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookmarkContentPreview() {
    NewsRoomTheme {
        BookmarkContent(
            state = PreviewData.bookmarkState,
            navigateToDetails = { },
        )
    }
}
