package com.reels.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.reels.presentation.spotlight.DummySpotlightData
import com.reels.presentation.spotlight.SpotlightV2
import kotlinx.coroutines.launch

private val paddingVideo = 24.dp
private val paddingVideoBottom = 68.dp
private val heightForPreview = 0.4f

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightTabPreviewer(
    spotlights: List<SpotlightV2>,
    expanded: Boolean,
    onExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val previews = remember {
        val previewsSpotlight = mutableListOf<String>()
        spotlights.forEach { spot ->
            previewsSpotlight.add(spot.reels[0])
        }
        previewsSpotlight
    }

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

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

    Column(
        modifier = modifier.background(Color.White),
    ) {
        TopicTabs(
            currentPage = pagerState.currentPage,
            onTabClick = { tabIndex ->
                scope.launch {
                    pagerState.animateScrollToPage(tabIndex)
                }
            },
            isVisible = !expanded,
            items = spotlights.map { it.title },
        )

        ReelsPager(
            expanded = expanded,
            pagerState = pagerState,
            onExpand = onExpand,
            previews = previews,
            reels = spotlights[pagerState.currentPage].reels,
            animatedMaxHeight = animatedMaxHeight,
            animatedPaddingVideo = animatedPaddingVideo,
            animatedPaddingVideoBottom = animatedPaddingVideoBottom,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpotlightTabPreviewer_not_expanded_Preview() {
    SpotlightTabPreviewer(
        spotlights = DummySpotlightData.spotlightV2,
        expanded = false,
        onExpand = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun SpotlightTabPreviewer_expanded_Preview() {
    SpotlightTabPreviewer(
        spotlights = DummySpotlightData.spotlightV2,
        expanded = true,
        onExpand = {},
    )
}

@ExperimentalPagerApi
@Composable
private fun TopicTabs(
    isVisible: Boolean,
    currentPage: Int,
    items: List<String>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(2000)),
        exit = fadeOut(),
    ) {
        ScrollableTabRow(
            modifier = modifier,
            containerColor = Color.Transparent,
            selectedTabIndex = currentPage,
            edgePadding = 0.dp,
            indicator = {},
            divider = {},

        ) {
            items.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (currentPage == index) Color.Black else Color.LightGray,
                            style = MaterialTheme.typography.displayMedium,
                            textAlign = TextAlign.Start,
                            modifier = Modifier,
                        )
                    },
                    selected = currentPage == index,
                    onClick = {
                        onTabClick.invoke(index)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun TopicTabsPreview() {
    TopicTabs(
        isVisible = true,
        currentPage = 0,
        onTabClick = { _ -> },
        items = DummySpotlightData.spotlightV2.map { it.title },
    )
}
