package com.reels.presentation.composables

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.reels.presentation.R
import com.reels.presentation.spotlight.Reel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReelVideoPlayer(
    reel: Reel,
//    shouldPlay: Boolean,
//    isMuted: Boolean,
//    onMuted: (Boolean) -> Unit,
//    isScrolling: Boolean,
//    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {

    var volumeIconVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val videoModifier = modifier

    val exoPlayer = remember(reel.reelUrl) {
        ExoPlayer.Builder(context).build().apply {
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            repeatMode = Player.REPEAT_MODE_ONE

            setHandleAudioBecomingNoisy(true)
            val defaultDataSource = DefaultHttpDataSource.Factory()
            val source = ProgressiveMediaSource
                .Factory(defaultDataSource)
                .createMediaSource(MediaItem.fromUri(reel.reelUrl))
            setMediaSource(source)
            prepare()
        }
    }

    Box {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
                // playerView
            },
            modifier = videoModifier,
            update = {
//                exoPlayer.volume = if (isMuted) 0f else 1f
//                exoPlayer.playWhenReady = shouldPlay
            },
        )

        AnimatedVisibility(
            visible = volumeIconVisibility,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy),
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center),
        ) {
//            Icon(
//                painter = painterResource(id = if (isMuted) R.drawable.ic_volume_off else R.drawable.ic_volume_on),
//                contentDescription = null,
//                tint = Color.White.copy(0.75f),
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .size(100.dp),
//            )
        }
    }

    DisposableEffect(key1 = true) {
        onDispose {
            exoPlayer.release()
        }
    }
}
