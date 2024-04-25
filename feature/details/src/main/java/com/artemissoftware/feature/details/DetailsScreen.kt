package com.artemissoftware.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.feature.details.composables.DetailsTopBar
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.core.ui.R
import com.core.ui.composables.UIEventsManager

@Composable
internal fun DetailsScreen(
    popBackStack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    DetailsContent(
        state = state,
        event = viewModel::onTriggerEvent,
        popBackStack = popBackStack,
    )

    UIEventsManager(
        uiEvent = viewModel.uiEvent,
    )
}

@Composable
private fun DetailsContent(
    state: DetailsState,
    event: (DetailsEvent) -> Unit,
    popBackStack: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                event.invoke(DetailsEvent.OpenWebContent)
            },
            onShareClick = {
                event.invoke(DetailsEvent.ShareArticle)
            },
            onBookMarkClick = {
                event(DetailsEvent.UpdateBookMark)
            },
            isBookmarked = state.isBookmarked,
            onBackClick = popBackStack,
        )

        state.article?.let { article ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.spacing3)
                    .padding(horizontal = MaterialTheme.spacing.spacing3),
            ) {
                item {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data(article.urlToImage)
                            .placeholder(R.drawable.ic_news)
                            .error(R.drawable.ic_news)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.dimension.articleImageHeight)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing3))

                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.palette.extraColors.textTitle,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing2))

                    Text(
                        text = article.content,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.palette.extraColors.body,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsContentPreview() {
    NewsRoomTheme {
        DetailsContent(
            state = PreviewData.detailsState,
            event = {},
            popBackStack = {},
        )
    }
}
