package com.artemissoftware.feature.news

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.artemissoftware.newsroom.core.model.Article
import com.core.presentation.composables.article.ArticlesList
import com.core.ui.composables.Dialog
import kotlinx.coroutines.delay

@Composable
internal fun NewsScreen(
//    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    NewsContent(
        state = state,
        event = viewModel::onTriggerEvent,
        navigateToDetails = navigateToDetails,
        navigateToSearch = navigateToSearch,
    )
}

@Composable
private fun NewsContent(
//    articles: LazyPagingItems<Article>,
    state: NewsState,
    event: (NewsEvent) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    val scrollState = rememberScrollState(initial = state.scrollValue)

    // Update the maxScrollingValue
    LaunchedEffect(key1 = scrollState.maxValue) {
        event(NewsEvent.UpdateMaxScrollingValue(scrollState.maxValue))
    }

    // Save the state of the scrolling position
    LaunchedEffect(key1 = scrollState.value) {
        event(NewsEvent.UpdateScrollValue(scrollState.value))
    }

    // Animate the scrolling
    LaunchedEffect(key1 = state.maxScrollingValue) {
        delay(500)
        if (state.maxScrollingValue > 0) {
            scrollState.animateScrollTo(
                value = state.maxScrollingValue,
                animationSpec = infiniteRepeatable(
                    tween(
                        durationMillis = (state.maxScrollingValue - state.scrollValue) * 50_000 / state.maxScrollingValue,
                        easing = LinearEasing,
                        delayMillis = 1000,
                    ),
                ),
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.spacing3)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2),
    ) {
        FilledTonalIconButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = MaterialTheme.spacing.spacing3),
            onClick = navigateToSearch,
            content = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
        )

        Text(
            text = state.getTitles(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.palette.extraColors.placeHolder,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.spacing3)
                .horizontalScroll(state = scrollState, enabled = false),
        )

        ArticlesList(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing3),
            articles = state.articles,
            onClick = {
                navigateToDetails(it)
            },
        )
    }

    state.dialogData?.let {
        Dialog(
            dialogData = it,
            onDismiss = {
                event(NewsEvent.CloseDialog)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsContentPreview() {
    NewsRoomTheme {
        NewsContent(
            state = PreviewData.state,
            event = {},
            navigateToDetails = {},
            navigateToSearch = {},
        )
    }
}
