package com.reels.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.reels.presentation.spotlight.DummySpotlightData

private val paddingVideo = 24.dp
private val paddingVideoBottom = 68.dp
private val heightForPreview = 0.4f

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReelsPager(
    pagerState: PagerState,
    expanded: Boolean,
    onExpand: () -> Unit,
    previews: List<String>,
    reels: List<String>,
    modifier: Modifier = Modifier,
    animatedMaxHeight: Float,
    animatedPaddingVideo: Dp,
    animatedPaddingVideoBottom: Dp,
) {
    val reelsLOLO = DummySpotlightData.reels
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(0.dp),
        itemSpacing = 0.dp,
        count = previews.size,
        modifier = modifier
            .padding(horizontal = animatedPaddingVideo)
            .padding(top = animatedPaddingVideo)
            .padding(bottom = animatedPaddingVideoBottom)
            .fillMaxHeight(animatedMaxHeight)
            .clickable { onExpand() },
    ) { index ->
        VerticalPager(
            count = if (expanded) reels.size else 1,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier,
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

                ReelVideoPlayer(
                    modifier = Modifier
                        .fillMaxSize(),
                    reel = reelsLOLO[0],
                )

//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = if (index == 0) Color.Green else Color.Magenta),
//                ) {
//                    Text(
//                        text = reels[index],
//                        style = MaterialTheme.typography.displayLarge,
//                    )
//                }

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

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun ReelsPager_not_expanded_Preview() {
    val pagerState = rememberPagerState()

    ReelsPager(
        pagerState = pagerState,
        animatedMaxHeight = heightForPreview,
        animatedPaddingVideo = paddingVideo,
        animatedPaddingVideoBottom = paddingVideoBottom,
        expanded = false,
        onExpand = {},
        previews = DummySpotlightData.spotlightV2.map { it.title },
        reels = DummySpotlightData.spotlightV2[0].reels,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun ReelsPager_expanded_Preview() {
    val pagerState = rememberPagerState()

    ReelsPager(
        pagerState = pagerState,
        animatedMaxHeight = heightForPreview,
        animatedPaddingVideo = paddingVideo,
        animatedPaddingVideoBottom = paddingVideoBottom,
        expanded = true,
        onExpand = {},
        previews = DummySpotlightData.spotlightV2.map { it.title },
        reels = DummySpotlightData.spotlightV2[0].reels,
    )
}
