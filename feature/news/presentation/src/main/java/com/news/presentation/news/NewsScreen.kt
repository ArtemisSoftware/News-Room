package com.news.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.ui.theme.NewsRoomTheme
import com.core.ui.theme.spacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.news.presentation.R
import com.news.presentation.news.composables.ArticlesList
import com.news.presentation.news.models.Topic

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    // val articles = viewModel.news.collectAsLazyPagingItems()

    NewsScreenContent(
        state = state.value,
        events = viewModel::onTriggerEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
private fun NewsScreenContent(
    state: NewsState,
    events: ((NewsEvents) -> Unit),
) {
//    val titles by remember {
//        derivedStateOf {
//            if (articles.itemCount > 10) {
//                articles.itemSnapshotList.items
//                    .slice(IntRange(start = 0, endInclusive = 9))
//                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
//            } else {
//                ""
//            }
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.mediumPadding)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.mediumPadding),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_news_room),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MaterialTheme.spacing.mediumPadding),
        )

//        SearchBar(
//            modifier = Modifier
//                .padding(horizontal = MediumPadding1)
//                .fillMaxWidth(),
//            text = "",
//            readOnly = true,
//            onValueChange = {},
//            onSearch = {},
//            onClick = navigateToSearch
//        )

//        val scrollState = rememberScrollState(initial = state.scrollValue)
//
//        Text(
//            text = titles, modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = MediumPadding1)
//                .horizontalScroll(scrollState, enabled = false),
//            fontSize = 12.sp,
//            color = colorResource(id = R.color.placeholder)
//        )
//
//        // Update the maxScrollingValue
//        LaunchedEffect(key1 = scrollState.maxValue) {
//            event(HomeEvent.UpdateMaxScrollingValue(scrollState.maxValue))
//        }
//        // Save the state of the scrolling position
//        LaunchedEffect(key1 = scrollState.value) {
//            event(HomeEvent.UpdateScrollValue(scrollState.value))
//        }
//        // Animate the scrolling
//        LaunchedEffect(key1 = state.maxScrollingValue) {
//            delay(500)
//            if (state.maxScrollingValue > 0) {
//                scrollState.animateScrollTo(
//                    value = state.maxScrollingValue,
//                    animationSpec = infiniteRepeatable(
//                        tween(
//                            durationMillis = (state.maxScrollingValue - state.scrollValue) * 50_000 / state.maxScrollingValue,
//                            easing = LinearEasing,
//                            delayMillis = 1000
//                        )
//                    )
//                )
//            }
//        }

        ArticlesList(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.mediumPadding),
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
    NewsRoomTheme {
        NewsScreenContent(
            events = {},
            state = NewsState(
                topics = listOf(Topic.Home, Topic.Settings, Topic.Profile),
                articles = MockData.articleList,
            ),
        )
    }
}
