package com.reels.presentation.spotlight

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalSnapperApi::class)
@Composable
fun VideoPage(
    allitems: List<SpotlightV2>,
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpand: () -> Unit,
) {
    val animH by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.3f,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val aniDP by animateDpAsState(
        targetValue = if (expanded) 0.dp else 24.dp,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val lazyListState: LazyListState = rememberLazyListState()
    val stateFling = rememberSnapperFlingBehavior(
        lazyListState = lazyListState,
        snapOffsetForItem = SnapOffsets.Start,
        maximumFlingDistance = {
            1F
        },
        endContentPadding = 24.dp
    )

    val pagerState = rememberPagerState()
    val pagerStateHorizontal = rememberPagerState()

    Column(modifier = modifier) {
        if (expanded == false) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = lazyListState,
                flingBehavior = stateFling,
                verticalAlignment = Alignment.CenterVertically,
                userScrollEnabled = true,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                itemsIndexed(allitems) { index, item ->
                    Box(
                        modifier = Modifier
                            .padding(end = 64.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = item.title,
                            color = if (index == lazyListState.firstVisibleItemIndex) Color.Red else Color.White,
                            style = MaterialTheme.typography.displayLarge,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.align(
                                Alignment.CenterStart,
                            ),
                        )
                    }
                }
            }
        }

        if(expanded == false) {
            HorizontalPager(
                state = pagerStateHorizontal,
                contentPadding = PaddingValues(0.dp),
                itemSpacing = 0.dp,
                count = 1,
                modifier = Modifier
                    .padding(aniDP)
                    .fillMaxHeight(animH)
                    .background(color = Color.Red)
                    .clickable { onExpand() },
            ) { index ->
                Box(
                    modifier = Modifier
                        //                    .fillMaxHeight()
                        //                    .fillMaxWidth()
                        .fillMaxSize()
                        .background(color = if (index == 0) Color.Green else Color.Magenta),
                ) {
                    Text(
                        text = allitems[lazyListState.firstVisibleItemIndex].reels[0],
                        style = MaterialTheme.typography.displayLarge,
                    )
                }
            }
        }
        else{





        VerticalPager(
            count = if (expanded) allitems[lazyListState.firstVisibleItemIndex].reels.size else 1,
            state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(aniDP)
                .fillMaxHeight(animH)
                .background(color = Color.Red)
                .clickable { onExpand() }
                ,
        ) { index ->
            Box(modifier = Modifier.background(color = Color.LightGray)) {
 //                val shouldPlay by remember(pagerState) {
 //                    derivedStateOf {
 //                        (abs(currentPageOffset) < .5 && currentPage == index) || (
 //                                abs(
 //                                    currentPageOffset,
 //                                ) > .5 && pagerState.targetPage == index
 //                                )
 //                    }
 //                }

                Box(
                    modifier = Modifier
                        //                    .fillMaxHeight()
                        //                    .fillMaxWidth()
                        .fillMaxSize()
                        .background(color = if (index == 0) Color.Green else Color.Magenta),
                ){
                    Text(
                        text = allitems[lazyListState.firstVisibleItemIndex].reels[index],
                        style = MaterialTheme.typography.displayLarge,
                    )
                }

 //                VideoPlayer_(
 //                    modifier = Modifier.fillMaxSize(),
 //                    reel = reels[index],
 //                    shouldPlay = shouldPlay,
 //                    isMuted = isMuted,
 //                    isExpanded = expanded,
 //                    isScrolling = pagerState.isScrollInProgress,
 //                    onMuted = {
 //                        isMuted = it
 //                    },
 //                )

 //                VideoDataPanel(
 //                    modifier = Modifier
 //                        .padding(horizontal = 24.dp)
 //                        .padding(bottom = 16.dp)
 //                        .fillMaxWidth(0.8F)
 //                        .align(Alignment.BottomStart).background(color = Color.Red),
 //                )
            }
        }
    }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalSnapperApi::class)
@Composable
fun VideoPage_v1() {
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
            },

        )

        val pagerState = rememberPagerState()
        val corotine = rememberCoroutineScope()
        LaunchedEffect(key1 = lazyListState.firstVisibleItemIndex) {
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
    VideoPage(
        allitems = DummySpotlightData.spotlightV2,
        modifier = Modifier.wrapContentSize(),
        expanded = false,
        onExpand = {},
    )
}
