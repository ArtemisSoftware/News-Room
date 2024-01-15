package com.reels.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.reels.presentation.spotlight.DummySpotlightData
import com.reels.presentation.spotlight.SpotlightV2
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.SnapperFlingBehavior
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch

private val paddingVideo = 24.dp
private val paddingVideoBottom = 68.dp
private val heightForPreview = 0.4f

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightCarrousel_Final(
    spotlights: List<SpotlightV2>,
    expanded: Boolean,
    onExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val animatedMaxHeight by animateFloatAsState(
        targetValue = if (expanded) 1f else heightForPreview,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val animatedPaddingVideo by animateDpAsState(
        targetValue = if (expanded) 0.dp else paddingVideo,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val animatedPaddingVideoBottom by animateDpAsState(
        targetValue = if (expanded) 0.dp else paddingVideoBottom,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val previewReels = mutableListOf<String>()
    spotlights.forEach { spot ->
        previewReels.add(spot.reels[0])
    }

    val pagerState = rememberPagerState()
    Column(
        modifier = modifier.background(Color.White),
    ) {
        TopicTabs(
            pagerState = pagerState,
            isVisible = !expanded,
            items = spotlights.map { it.title },
        )
        DoubleContent(
            expanded = expanded,
                pagerState = pagerState,
                onExpand = onExpand,
            reelsHorizontal = previewReels,
                animatedMaxHeight = animatedMaxHeight,
                animatedPaddingVideo = animatedPaddingVideo,
                animatedPaddingVideoBottom = animatedPaddingVideoBottom,
            reelsVertical = spotlights[pagerState.currentPage].reels, // TODO: mudar
        )
//        if (expanded) {
//            ReelContent(
//                animatedMaxHeight = animatedMaxHeight,
//                animatedPaddingVideo = animatedPaddingVideo,
//                animatedPaddingVideoBottom = animatedPaddingVideoBottom,
//                onExpand = onExpand,
//                reels = spotlights[pagerState.currentPage].reels, // TODO: mudar
//            )
//        } else {
//            SpotlightContent(
//                pagerState = pagerState,
//                onExpand = onExpand,
//                reels = previewReels,
//            )
//        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DoubleContent(
    pagerState: PagerState,
    expanded: Boolean,
    onExpand: () -> Unit,
    reelsHorizontal: List<String>,
    modifier: Modifier = Modifier,
    animatedMaxHeight: Float,
    animatedPaddingVideo: Dp,
    animatedPaddingVideoBottom: Dp,
    reelsVertical: List<String>,
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(0.dp),
        itemSpacing = 0.dp,
        count = reelsHorizontal.size,
        modifier = modifier
            .padding(horizontal = animatedPaddingVideo)
            .padding(top = animatedPaddingVideo)
            .padding(bottom = animatedPaddingVideoBottom)
            .fillMaxHeight(animatedMaxHeight)
            .background(color = Color.Yellow)
            .clickable { onExpand() },
    ) { index ->
        VerticalPager(
            count = if (expanded) reelsVertical.size else 1,
//        state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Red)
                /*.clickable { onExpand() }*/,
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
                ) {
                    Text(
                        text = reelsVertical[index]/*"allitems[lazyListState.firstVisibleItemIndex].reels[index]"*/,
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










@Preview(showBackground = true)
@Composable
private fun SpotlightCarrousel_FinalPreview() {
    SpotlightCarrousel_Final(
        spotlights = DummySpotlightData.spotlightV2,
        expanded = false,
        onExpand = {},
    )
}

@ExperimentalPagerApi
@Composable
private fun TopicTabs(
    isVisible: Boolean,
    pagerState: PagerState,
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(2000)),
        exit = fadeOut(),
    ) {

        ScrollableTabRow(
            modifier = modifier,
            containerColor = Color.Transparent,
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            indicator = {},
            divider = {},

            ) {
            items.forEachIndexed { index, title ->

                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (pagerState.currentPage == index) Color.Black else Color.LightGray,
                            style = MaterialTheme.typography.displayLarge,
                            textAlign = TextAlign.Start,
                            modifier = Modifier,
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightContent(
    pagerState: PagerState,
    onExpand: () -> Unit,
    reels: List<String>,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(0.dp),
        itemSpacing = 0.dp,
        count = reels.size,
        modifier = modifier
            .padding(horizontal = paddingVideo)
            .padding(top = paddingVideo)
            .padding(bottom = paddingVideoBottom)
            .fillMaxHeight(heightForPreview)
            .background(color = Color.Red)
            .clickable { onExpand() },
    ) { index ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (index == 0) Color.Green else Color.Magenta),
        ) {
            Text(
                text = reels[index],
                style = MaterialTheme.typography.displayLarge,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReelContent(
    animatedMaxHeight: Float,
    animatedPaddingVideo: Dp,
    animatedPaddingVideoBottom: Dp,
    onExpand: () -> Unit,
    reels: List<String>,
) {
    VerticalPager(
        count = reels.size/*if (expanded) allitems[lazyListState.firstVisibleItemIndex].reels.size else*/ /*1*/,
//        state = pagerState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = animatedPaddingVideo)
            .padding(top = animatedPaddingVideo)
            .padding(bottom = animatedPaddingVideoBottom)
            .fillMaxHeight(animatedMaxHeight)
            .background(color = Color.Red)
            .clickable { onExpand() },
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
            ) {
                Text(
                    text = reels[index]/*"allitems[lazyListState.firstVisibleItemIndex].reels[index]"*/,
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightCarrousel__(
    spotlights: List<SpotlightV2>,
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "About", "Settings", "More", "Something", "Everything")

    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.background(Color.White),
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }

//    Column(modifier = Modifier.fillMaxWidth()) {
//        ScrollableTabRow(
//            containerColor = Color.Transparent,
//            selectedTabIndex = tabIndex,
//            edgePadding = 0.dp,
//            indicator = {},
//            divider = {},
//        ) {
//            tabs.forEachIndexed { index, title ->
//                Tab(
//                    text = {
//                        Text(
//                            text = title,
//                            color = Color.Black,
//                            style = MaterialTheme.typography.displayLarge,
//                            textAlign = TextAlign.Start,
//                            modifier = Modifier,
//                        )
//                    },
//                    selected = tabIndex == index,
//                    onClick = { tabIndex = index },
//                )
//            }
//        }
//        when (tabIndex) {
//            0 -> lolo(tabs[tabIndex])
//            1 -> lolo(tabs[tabIndex])
//            2 -> lolo(tabs[tabIndex])
//            3 -> lolo(tabs[tabIndex])
//            4 -> lolo(tabs[tabIndex])
//            5 -> lolo(tabs[tabIndex])
//        }
//
//
//        TabRow(selectedTabIndex = tabIndex) {
//            tabs.forEachIndexed { index, title ->
//                Tab(text = { Text(title) },
//                    selected = tabIndex == index,
//                    onClick = { tabIndex = index },
//                )
//            }
//        }
//
//        when (tabIndex) {
//            0 -> lolo(tabs[tabIndex])
//            1 -> lolo(tabs[tabIndex])
//            2 -> lolo(tabs[tabIndex])
//        }
//    }
}

@Preview(showBackground = true)
@Composable
private fun SpotlightCarrousel__() {
    SpotlightCarrousel__(
        spotlights = DummySpotlightData.spotlightV2,
    )
}

// on below line we are
// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf("Home", "About", "Settings", "More", "Something", "Everything")
    // on below line we are creating
    // a variable for the scope.
    val scope = rememberCoroutineScope()
    // on below line we are creating a
    // individual row for our tab layout.
    ScrollableTabRow(
        // on below line we are specifying
        // the selected index.
        selectedTabIndex = pagerState.currentPage,

        // on below line we are specifying content color.
        contentColor = Color.White,

    ) {
        // on below line we are specifying icon
        // and text for the individual tab item
        list.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                // on below line we are specifying the text for
                // the each tab item and we are calling data
                // from the list which we have created.
                text = {
                    Text(
                        text = list[index],
                        // on below line we are specifying the text color
                        // for the text in that tab
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray,
                    )
                },
                // on below line we are specifying
                // the tab which is selected.
                selected = pagerState.currentPage == index,
                // on below line we are specifying the
                // on click for the tab which is selected.
                onClick = {
                    // on below line we are specifying the scope.
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

// on below line we are creating a tab content method
// in which we will be displaying the individual page of our tab .
@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    // on below line we are creating
    // horizontal pager for our tab layout.
    HorizontalPager(
        contentPadding = PaddingValues(0.dp),
        itemSpacing = 0.dp,
        state = pagerState,
        count = 6,
    ) {
            // on below line we are specifying
            // the different pages.
            page ->
        when (page) {
            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            0 -> TabContentScreen(data = "Welcome to Home Screen")
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            1 -> TabContentScreen(data = "Welcome to Shopping Screen")
            // on below line we are calling tab content screen
            // and specifying data as Settings Screen.
            2 -> TabContentScreen(data = "Welcome to Settings Screen")
            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            3 -> TabContentScreen(data = "Welcome to Home Screen")
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            4 -> TabContentScreen(data = "Welcome to Shopping Screen")
            // on below line we are calling tab content screen
            // and specifying data as Settings Screen.
            5 -> TabContentScreen(data = "Welcome to Settings Screen")
        }
    }
}

// on below line we are creating a Tab Content
// Screen for displaying a simple text message.
@Composable
fun TabContentScreen(data: String) {
    // on below line we are creating a column
    Column(
        // in this column we are specifying modifier
        // and aligning it center of the screen on below lines.
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // in this column we are specifying the text
        lolo(item = data)
    }
}

@Composable
fun lolo(item: String) {
    Box(
        modifier = Modifier
            .padding(end = 64.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(
                Alignment.CenterStart,
            ),
        )
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun SpotlightCarrousel(
    spotlights: List<SpotlightV2>,
    expanded: Boolean,
    onExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val animatedMaxHeight by animateFloatAsState(
        targetValue = if (expanded) 1f else heightForPreview,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val animatedPaddingVideo by animateDpAsState(
        targetValue = if (expanded) 0.dp else paddingVideo,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val animatedPaddingVideoBottom by animateDpAsState(
        targetValue = if (expanded) 0.dp else paddingVideoBottom,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    )

    val lazyListState: LazyListState = rememberLazyListState()
    val snapperFlingState = rememberSnapperFlingBehavior(
        lazyListState = lazyListState,
        snapOffsetForItem = SnapOffsets.Start,
        maximumFlingDistance = {
            1F
        },
        endContentPadding = 24.dp,
    )

    val firstVisibleTopicIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

    Column(modifier = modifier) {
        Topics(
            lazyListState = lazyListState,
            snapperFlingState = snapperFlingState,
            firstVisibleTopicIndex = firstVisibleTopicIndex,
            items = spotlights.map { it.title },
            isVisible = !expanded,
            modifier = Modifier.fillMaxWidth(),
        )

//        if (!expanded) {
//            SpotlightVideoPager(
//                onExpand = onExpand,
//                reel = spotlights[0].reels[0], // TODO: mudar
//            )
//        } else {
        ReelPager(
            animatedMaxHeight = animatedMaxHeight,
            animatedPaddingVideo = animatedPaddingVideo,
            animatedPaddingVideoBottom = animatedPaddingVideoBottom,
            onExpand = onExpand,
            reels = spotlights[0].reels, // TODO: mudar
        )
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpotlightCarrouselPreview_not_expanded() {
    SpotlightCarrousel(
        spotlights = DummySpotlightData.spotlightV2,
        modifier = Modifier.wrapContentSize(),
        expanded = false,
        onExpand = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun SpotlightCarrouselPreview_expanded() {
    SpotlightCarrousel(
        spotlights = DummySpotlightData.spotlightV2,
        modifier = Modifier.wrapContentSize(),
        expanded = true,
        onExpand = {},
    )
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
private fun Topics(
    isVisible: Boolean,
    items: List<String>,
    modifier: Modifier,
    lazyListState: LazyListState,
    snapperFlingState: SnapperFlingBehavior,
    firstVisibleTopicIndex: Int,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LazyRow(
            modifier = modifier,
            state = lazyListState,
            flingBehavior = snapperFlingState,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = true,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            itemsIndexed(items) { index, item ->

                val bgColor: Color by animateColorAsState(
                    targetValue = if (index == firstVisibleTopicIndex) Color.Red else Color.Blue,
                    animationSpec = tween(
                        durationMillis = 100,
                        delayMillis = 250,
                        easing = LinearEasing,
                    ),
                )

                Box(
                    modifier = Modifier
                        .padding(end = 64.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = item,
                        color = /*if (index == lazyListState.firstVisibleItemIndex)*/ bgColor /*else Color.White*/,
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
}

@OptIn(ExperimentalSnapperApi::class)
@Preview(showBackground = true)
@Composable
private fun TopicsPreview() {
    val lazyListState: LazyListState = rememberLazyListState()
    val snapperFlingState = rememberSnapperFlingBehavior(
        lazyListState = lazyListState,
        snapOffsetForItem = SnapOffsets.Start,
        maximumFlingDistance = {
            1F
        },
        endContentPadding = 24.dp,
    )

    Topics(
        isVisible = true,
        items = DummySpotlightData.spotlightV2.map { it.title },
        modifier = Modifier.fillMaxWidth(),
        lazyListState = lazyListState,
        snapperFlingState = snapperFlingState,
        firstVisibleTopicIndex = 0,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightVideoPager(
    onExpand: () -> Unit,
    reel: String,
) {
    HorizontalPager(
//        state = pagerStateHorizontal,
        contentPadding = PaddingValues(0.dp),
        itemSpacing = 0.dp,
        count = 1,
        modifier = Modifier
            .padding(horizontal = paddingVideo)
            .padding(top = paddingVideo)
            .padding(bottom = paddingVideoBottom)
            .fillMaxHeight(heightForPreview)
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
                text = reel,
                style = MaterialTheme.typography.displayLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpotlightVideoPagerPreview() {
    SpotlightVideoPager(
        reel = "AAA",
        onExpand = {},
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ReelPager(
    animatedMaxHeight: Float,
    animatedPaddingVideo: Dp,
    animatedPaddingVideoBottom: Dp,
    onExpand: () -> Unit,
    reels: List<String>,
) {
    VerticalPager(
        count = reels.size/*if (expanded) allitems[lazyListState.firstVisibleItemIndex].reels.size else*/ /*1*/,
//        state = pagerState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = animatedPaddingVideo)
            .padding(top = animatedPaddingVideo)
            .padding(bottom = animatedPaddingVideoBottom)
            .fillMaxHeight(animatedMaxHeight)
            .background(color = Color.Red)
            .clickable { onExpand() },
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
            ) {
                Text(
                    text = reels[index]/*"allitems[lazyListState.firstVisibleItemIndex].reels[index]"*/,
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

@Preview(showBackground = true)
@Composable
private fun ReelPagerPreview() {
    ReelPager(
        animatedMaxHeight = heightForPreview,
        animatedPaddingVideo = paddingVideo,
        animatedPaddingVideoBottom = paddingVideoBottom,
        onExpand = {},
        reels = DummySpotlightData.spotlightV2[0].reels,
    )
}
