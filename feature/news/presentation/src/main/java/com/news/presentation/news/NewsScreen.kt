package com.news.presentation.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.news.domain.models.Article
import com.news.presentation.news.composables.ArticleCard
import com.news.presentation.news.composables.SearchBar
import com.news.presentation.news.composables.UserTopBar
import com.news.presentation.news.models.Topic

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

    NewsScreenContent(
        state = state.value,
        events = viewModel::onTriggerEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
private fun NewsScreenContent(
    state: NewsState,
    events: ((NewsEvents) -> Unit),
) {
    val pagerState = rememberPagerState()

    Scaffold(
        // scaffoldState = scaffoldState,
        topBar = {
            UserTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        },
        bottomBar = {
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it),
            ) {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    onSearchClicked = { text ->
                        events.invoke(NewsEvents.searchArticles(query = text))
                    }
                )

                // Spacer
                // Spacer(modifier = Modifier.height(32.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    itemSpacing = 16.dp,
                    count = state.articles.size,
                ) { page ->

//                    ArticleCard(
//                        article = state.articles[0],
//                        modifier = Modifier.fillMaxWidth(),
//                    )
                }

//                Spacer(modifier = Modifier.height(30.dp))
//
// //                // Bottom Section
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, end = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Bottom,
//                ) {
// //                    // Heading Section
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = "Flows List",
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black,
//                            fontSize = 30.sp,
//                        )
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                imageVector = Icons.Default.ArrowDropDown,
//                                contentDescription = "",
//                                tint = Color.Black,
//                            )
//                        }
//                    }
//
//                    // see all section
//                    Text(
//                        text = "See all",
//                        color = Color.Gray,
//                        fontSize = 16.sp,
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                // Pending Action List
//
//                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
//                    itemsIndexed(state.articles) { index, flow ->
//                        // Pending Actions List Item
//                        ArticleResumeCard(flow)
//                    }
//                }
            }
        },
        modifier = Modifier,
        // backgroundColor = colorResource(id = R.color.wheat),
    )
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenContentPreview() {
    NewsScreenContent(
        events = {},
        state = NewsState(
            topics = listOf(Topic.Home, Topic.Settings, Topic.Profile),
            articles = listOf(
                Article.mockArticle,
                Article.mockArticle,
                Article.mockArticle,
            ),
        ),
    )
}
