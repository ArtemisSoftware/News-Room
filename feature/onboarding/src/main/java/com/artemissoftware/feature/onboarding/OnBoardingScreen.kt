package com.artemissoftware.feature.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.feature.onboarding.composables.OnBoardingPage
import com.artemissoftware.feature.onboarding.composables.PagerIndicator
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.composables.buttons.NRButton
import com.artemissoftware.newsroom.core.designsystem.composables.buttons.NRTextButton
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.core.ui.composables.UIEventsManager
import kotlinx.coroutines.launch

@Composable
internal fun OnBoardingScreen(
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    OnBoardingScreenContent(
        state = state,
        event = viewModel::onTriggerEvent,
    )

    UIEventsManager(
        uiEvent = viewModel.uiEvent,
        navigate = navigateToHome
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnBoardingScreenContent(
    state: OnBoardingState,
    event: (OnBoardingEvent) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.pages.size })
    LaunchedEffect(pagerState) {
        var previousPage = pagerState.currentPage
        snapshotFlow { pagerState.currentPage }.collect {
            if (previousPage != it) {
                event.invoke(OnBoardingEvent.SwipePage(index = it))
            }
            previousPage = it
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HorizontalPager(
            state = pagerState,
        ) { index ->
            OnBoardingPage(
                page = state.pages[index],
                image = state.image[index],
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.spacing4)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PagerIndicator(
                modifier = Modifier,
                pagesSize = state.pages.size,
                selectedPage = pagerState.currentPage,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (state.previewsText.asString().isNotEmpty()) {
                    NRTextButton(
                        text = state.previewsText.asString(),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1,
                                )
                            }
                        },
                    )
                }
                NRButton(
                    text = state.nextText.asString(),
                    onClick = {

                        if(state.reachedLastPage()){
                            event(OnBoardingEvent.Finish)
                        }
                        else {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1,
                                )
                            }
                        }
                    },
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@ThemePreviews
@Composable
private fun OnBoardingScreenContentPreview() {
    NewsRoomTheme {
        OnBoardingScreenContent(
            state = PreviewData.onBoardingState,
            event = {},
        )
    }
}
