package com.artemissoftware.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.core.ui.SearchBar
import com.core.ui.composables.ArticlesList

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit,
) {
    val state = viewModel.state.collectAsState().value

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
    navigateToDetails: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.spacing3)
            .statusBarsPadding(),
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (state.isSearching) 0.dp else MaterialTheme.spacing.spacing3),
            text = state.searchQuery,
            historyItems = state.historyItems,
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

        ArticlesList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.spacing3),
            articles = state.articles,
            onClick = {
                navigateToDetails.invoke(it.url)
            },
        )

        // TODO: quando houver paginação

//        state.articles?.let {
// //            val articles = it.collectAsLazyPagingItems()
//            ArticlesList(
//                articles = articles,
//                onClick = navigateToDetails
//            )
//        }
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