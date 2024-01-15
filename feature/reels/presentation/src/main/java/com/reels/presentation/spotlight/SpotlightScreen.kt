package com.reels.presentation.spotlight

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.reels.presentation.composables.ReelPlayer
import com.reels.presentation.composables.ReelTopBar
import com.reels.presentation.composables.SpotlightCarrousel_Final
import com.reels.presentation.composables.SpotlightTabPreviewer
import com.reels.presentation.composables.VideoDataPanel
import com.reels.presentation.composables.VideoPlayer_
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightScreen() {
//    val allitems = DummySpotlightData.spotlightV2
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Blue),
//    ) {
//        Text(
//            modifier = Modifier.align(Alignment.TopStart),
//            text = "Today's Date",
//            style = MaterialTheme.typography.displayMedium,
//        )
//
//        VideoPage(
//            expanded = expanded,
//            allitems = allitems,
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter),
//            onExpand = {
//                expanded = !expanded
//            },
//        )
//    }

    SpotlightScreenContent(
        state = SpotlightState(
            date = "",
            spotlights = DummySpotlightData.spotlightV2,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpotlightScreenContent(
    state: SpotlightState,
) {
    // TODO: vai para o state
    var expandReels by remember { mutableStateOf(false) }
    // var expandReels = state.expandReels
    // -----------

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = !expandReels,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                ReelTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
        },
        bottomBar = {
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 44.dp),
                    style = MaterialTheme.typography.displaySmall,
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Today,\n")
                        }
                        withStyle(style = SpanStyle(color = Color.LightGray)) {
                            append(getDate())
                        }
                    },
                )

                SpotlightTabPreviewer(
                    spotlights = DummySpotlightData.spotlightV2,
                    expanded = expandReels,
                    onExpand = {
                        expandReels = !expandReels
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                )
            }
        },
        modifier = Modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
private fun SpotlightScreenContentPreview() {
    SpotlightScreenContent(
        state = SpotlightState(
            date = "",
            expandReels = false,
            spotlights = DummySpotlightData.spotlightV2,
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
private fun SpotlightScreenContentPreview__expanded() {
    SpotlightScreenContent(
        state = SpotlightState(
            date = "",
            expandReels = true,
            spotlights = DummySpotlightData.spotlightV2,
        ),
    )
}

fun getDate(): String {
    val currentDate = LocalDate.now()
    val dayOfWeek = currentDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    val month = currentDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd"))

    return "$dayOfWeek, $formattedDate"
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpotlightScreen__v2() {
    val spotlights = DummySpotlightData.spotlight
    val reels = DummySpotlightData.reels

    val pagerState = rememberPagerState()

    var isMuted by remember {
        mutableStateOf(true)
    }

    var expanded by remember { mutableStateOf(false) }
    val animF by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.5f,
    )
    val animW by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.5f,
    )
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
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
        SpotlightSection(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6F))
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
//            .background(color = Color.Black),
//    ) {
        VerticalPager(
            count = if (expanded) spotlights.size else 1,
            state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                // .fillMaxHeight(0.4F)
//                .height(200.dp)
                .padding(aniDP)
                .fillMaxHeight(animH)
                .background(color = Color.Red)
                .clickable { expanded = !expanded }
                .align(Alignment.BottomCenter),
        ) { index ->
            Box(modifier = Modifier.background(color = Color.LightGray)) {
                val shouldPlay by remember(pagerState) {
                    derivedStateOf {
                        (kotlin.math.abs(currentPageOffset) < .5 && currentPage == index) || (
                            kotlin.math.abs(
                                currentPageOffset,
                            ) > .5 && pagerState.targetPage == index
                            )
                    }
                }

//                Box(modifier = Modifier
// //                    .fillMaxHeight()
// //                    .fillMaxWidth()
//                    .fillMaxSize()
//                    .background(color = if(index == 0) Color.Green else Color.Magenta)
//                )

                VideoPlayer_(
                    modifier = Modifier.fillMaxSize(),
                    reel = reels[index],
                    shouldPlay = shouldPlay,
                    isMuted = isMuted,
                    isExpanded = expanded,
                    isScrolling = pagerState.isScrollInProgress,
                    onMuted = {
                        isMuted = it
                    },
                )

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
@Composable
fun SpotlightScreen_V1() {
    val spotlights = DummySpotlightData.spotlight
    val reels = DummySpotlightData.reels

    val pagerState = rememberPagerState()

    var isMuted by remember {
        mutableStateOf(true)
    }

    var expanded by remember { mutableStateOf(false) }
    val animF by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.5f,
    )
    val animW by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.5f,
    )
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue),
    ) {
        SpotlightSection(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6F))
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
//            .background(color = Color.Black),
//    ) {
        VerticalPager(
            count = spotlights.size,
            state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(aniDP)
//                .fillMaxWidth(1F)
//                .fillMaxHeight(0.3f)
//                .fillMaxWidth(1F)
//                .fillMaxHeight(animH)
                .background(color = Color.Red)
                .clickable { expanded = !expanded }
                .align(Alignment.BottomCenter),
        ) { index ->
            Box {
                val shouldPlay by remember(pagerState) {
                    derivedStateOf {
                        (kotlin.math.abs(currentPageOffset) < .5 && currentPage == index) || (
                            kotlin.math.abs(
                                currentPageOffset,
                            ) > .5 && pagerState.targetPage == index
                            )
                    }
                }

                VideoPlayer_(
                    reel = reels[index],
                    shouldPlay = shouldPlay,
                    isMuted = isMuted,
                    isExpanded = expanded,
                    isScrolling = pagerState.isScrollInProgress,
                    onMuted = {
                        isMuted = it
                    },
                )

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
@Composable
fun a() {
    var expanded by remember { mutableStateOf(false) }
    val animF by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.5f,
    )
    val animW by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.5f,
    )
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

    val spotlights = DummySpotlightData.spotlight
    val reels = DummySpotlightData.reels

    val pagerState = rememberPagerState()

    var isMuted by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
        SpotlightSection(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6F))
//        Box(
//            Modifier
//                .padding(aniDP)
//                .fillMaxWidth(1F)
//                .fillMaxHeight(animH)
//                // .fillMaxSize(animF) // pass the animated Fraction here
//                .background(color = Color.Red)
//                .clickable { expanded = !expanded }
//                .align(Alignment.BottomCenter),
//        )

        VerticalPager(
            count = spotlights.size,
            state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(aniDP)
                .fillMaxWidth(1F)
                .fillMaxHeight(animH)
                // .fillMaxSize(animF) // pass the animated Fraction here
                .background(color = Color.Red)
                .clickable { expanded = !expanded }
                .align(Alignment.BottomCenter),
        ) { index ->
            Box {
                val shouldPlay by remember(pagerState) {
                    derivedStateOf {
                        (kotlin.math.abs(currentPageOffset) < .5 && currentPage == index) || (
                            kotlin.math.abs(
                                currentPageOffset,
                            ) > .5 && pagerState.targetPage == index
                            )
                    }
                }

                ReelPlayer(
                    reel = reels[index],
                    shouldPlay = shouldPlay,
                    isMuted = isMuted,
                    isScrolling = pagerState.isScrollInProgress,
                    onMuted = {
                        isMuted = it
                    },
                    onDoubleTap = {
                        // onLiked(index, it)
                    },
                )

//                VideoPlayer(
//                    uri = spotlights[index].getVideoUrl(),
//                    isScrolling = pagerState.isScrollInProgress,
//                    shouldPlay = shouldPlay,
//                    isMuted = isMuted,
//                    onMuted = {
//                        isMuted = it
//                    },
//                )

                VideoDataPanel(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(0.8F)
                        .align(Alignment.BottomStart).background(color = Color.Red),
                )
            }
        }
    }
}
