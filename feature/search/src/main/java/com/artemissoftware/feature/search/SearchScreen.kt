package com.artemissoftware.feature.search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artemissoftware.feature.PreviewData
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.artemissoftware.newsroom.core.model.Article
import com.core.presentation.composables.article.ArticleList
import com.core.ui.SearchBar
import com.core.ui.composables.Dialog

@Composable
internal fun SearchScreen(
    navigateToDetails: (Article) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    SearchContent(
        state = state,
        event = viewModel::onTriggerEvent,
        navigateToDetails = navigateToDetails,
    )
}

@Composable
private fun SearchContent(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    val searchBarSize = animateDpAsState(
        targetValue = if (state.isSearching) 0.dp else MaterialTheme.spacing.spacing3,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = searchBarSize.value)
            .statusBarsPadding(),
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = searchBarSize.value),
            historyItems = state.historyItems,
            text = state.searchQuery,
            isSearching = state.isSearching,
            onQueryChange = {
                event(SearchEvent.UpdateSearchQuery(searchQuery = it))
            },
            onSearch = {
                event(SearchEvent.SearchNews)
            },
            onActiveChange = {
                event(SearchEvent.ActivateSearch(isActive = it))
            },
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing3))

        ArticleList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.spacing3),
            articles = state.articles,
            pagedArticles = state.articlesPaged,
            onClick = { article ->
                navigateToDetails(article)
            },
        )
    }

    state.dialogData?.let {
        Dialog(
            dialogData = it,
            onDismiss = {
                event(SearchEvent.CloseDialog)
            },
        )
    }
}

@Preview
@Composable
private fun SearchContentPreview() {
    NewsRoomTheme {
        SearchContent(
            state = PreviewData.searchStateEmpty,
            event = {},
            navigateToDetails = {},
        )
    }
}

@Preview
@Composable
private fun SearchContent_with_history_Preview() {
    NewsRoomTheme {
        SearchContent(
            state = PreviewData.searchState,
            event = {},
            navigateToDetails = {},
        )
    }
}
