package com.reels.presentation.spotlight

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
fun VideoPage() {
    var count by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        val allitems = listOf("AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ", "KKK", "LLL", "")

        val lazyListState: LazyListState = rememberLazyListState()
        val stateFling = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
            snapOffsetForItem = SnapOffsets.Start,
            maximumFlingDistance = {
                1F
            }

        )

        val pagerState = rememberPagerState()
        val corotine = rememberCoroutineScope()
        LaunchedEffect(key1 = lazyListState.firstVisibleItemIndex){
            corotine.launch {
                pagerState.animateScrollToPage(page = lazyListState.firstVisibleItemIndex)
            }
        }

        Text(
            text = "items[currentPage].title - ${lazyListState.firstVisibleItemIndex}",
            style = MaterialTheme.typography.titleLarge,
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = lazyListState,
            flingBehavior = stateFling,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = true,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            items(allitems) {

                Box(
                    modifier = Modifier
                        .width(240.dp)
                        .padding(0.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        it.toString(),
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(
                            Alignment.CenterStart,
                        ),
                    )
                }
            }
        }


        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(32.dp),
            itemSpacing = 16.dp,
            count = allitems.size,
        ) {
            Text(
                text = allitems[it],
                style = MaterialTheme.typography.displayLarge,
            )
        }

//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(24.dp),
//            state = state,
//            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
//        ) {
//            items(allitems) { model ->
//                Text(
//                    text = model,
//                    style = MaterialTheme.typography.displayLarge,
//                )
//            }
//        }

//        val coroutineScope = rememberCoroutineScope()
//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    pagerState.animateScrollToPage(page = 2)
//                }
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//        ) {
//            Text(text = "Scroll to the third page")
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VideoPagePreview() {
    VideoPage()
}
