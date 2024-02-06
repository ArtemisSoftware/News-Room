package com.news.presentation.news

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
/*
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.news.presentation.news.composables.ArticlesList
import com.news.presentation.news.models.Topic
import kotlinx.coroutines.delay

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    // val articles = viewModel.news.collectAsLazyPagingItems()

    val scrollState = rememberScrollState(initial = state.scrollValue)

    // Update the maxScrollingValue
    LaunchedEffect(key1 = scrollState.maxValue) {
        viewModel.onTriggerEvent(NewsEvents.UpdateMaxScrollingValue(scrollState.maxValue))
    }

    // Save the state of the scrolling position
    LaunchedEffect(key1 = scrollState.value) {
        viewModel.onTriggerEvent(NewsEvents.UpdateScrollValue(scrollState.value))
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

    NewsScreenContent(
        state = state,
        events = viewModel::onTriggerEvent,
        scrollState = scrollState,
    )
}

@Composable
private fun NewsScreenContent(
    state: NewsState,
    events: (NewsEvents) -> Unit,
    scrollState: ScrollState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.mediumPadding)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.mediumPadding),
    ) {
        Text(
            text = state.getTitle(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.mediumPadding)
                .horizontalScroll(scrollState, enabled = false),
            fontSize = 12.sp,
            color = MaterialTheme.palette.Placeholder,
        )

        ArticlesList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.mediumPadding),
            articles = state.articles,
            onClick = { /*navigateToDetails*/ },
        )
    }

//    val pagerState = rememberPagerState()
//
//    Scaffold(
//        // scaffoldState = scaffoldState,
//        topBar = {
//            UserTopBar(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//            )
//        },
//        bottomBar = {
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues = it),
//            ) {
//                SearchBar(
//                    modifier = Modifier.fillMaxWidth(),
//                    onSearchClicked = { text ->
//                        events.invoke(NewsEvents.searchArticles(query = text))
//                    }
//                )
//
//                // Spacer
//                // Spacer(modifier = Modifier.height(32.dp))
//
// //                HorizontalPager(
// //                    state = pagerState,
// //                    modifier = Modifier
// //                        .fillMaxWidth()
// //                        .padding(horizontal = 16.dp),
// //                    itemSpacing = 16.dp,
// //                    count = state.articles.size,
// //                ) { page ->
// //
// ////                    ArticleCard(
// ////                        article = state.articles[0],
// ////                        modifier = Modifier.fillMaxWidth(),
// ////                    )
// //                }
//
// //                Spacer(modifier = Modifier.height(30.dp))
// //
// // //                // Bottom Section
// //                Row(
// //                    modifier = Modifier
// //                        .fillMaxWidth()
// //                        .padding(start = 16.dp, end = 16.dp),
// //                    horizontalArrangement = Arrangement.SpaceBetween,
// //                    verticalAlignment = Alignment.Bottom,
// //                ) {
// // //                    // Heading Section
// //                    Row(
// //                        verticalAlignment = Alignment.CenterVertically,
// //                        horizontalArrangement = Arrangement.SpaceBetween,
// //                    ) {
// //                        Text(
// //                            text = "Flows List",
// //                            fontWeight = FontWeight.Bold,
// //                            color = Color.Black,
// //                            fontSize = 30.sp,
// //                        )
// //                        IconButton(onClick = { /*TODO*/ }) {
// //                            Icon(
// //                                imageVector = Icons.Default.ArrowDropDown,
// //                                contentDescription = "",
// //                                tint = Color.Black,
// //                            )
// //                        }
// //                    }
// //
// //                    // see all section
// //                    Text(
// //                        text = "See all",
// //                        color = Color.Gray,
// //                        fontSize = 16.sp,
// //                    )
// //                }
// //
// //                Spacer(modifier = Modifier.height(20.dp))
// //
// //                // Pending Action List
// //
// //                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
// //                    itemsIndexed(state.articles) { index, flow ->
// //                        // Pending Actions List Item
// //                        ArticleResumeCard(flow)
// //                    }
// //                }
//            }
//        },
//        modifier = Modifier,
//        // backgroundColor = colorResource(id = R.color.wheat),
//    )
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenContentPreview() {
    com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme {
        val scrollState = rememberScrollState()
        NewsScreenContent(
            state = NewsState(
                topics = listOf(Topic.Home, Topic.Settings, Topic.Profile),
                articles = MockData.articleList,
            ),
            events = {},
            scrollState = scrollState,
        )
    }
}
*/