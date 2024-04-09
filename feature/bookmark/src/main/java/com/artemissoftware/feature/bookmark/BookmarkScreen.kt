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
import com.artemissoftware.feature.PreviewData
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.core.ui.R
import com.core.ui.composables.ArticlesList

@Composable
internal fun BookmarkScreen(
    navigateToDetails: (Int) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value
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

        ArticlesList(
            articles = state.articles,
            onClick = { article ->
                article.id?.let { navigateToDetails(it) }
            },
            modifier = Modifier.fillMaxWidth(),
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
