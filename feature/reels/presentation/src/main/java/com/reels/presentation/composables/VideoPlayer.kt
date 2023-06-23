package com.reels.presentation.composables

import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.reels.presentation.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VideoPlayer(
    uri: Uri,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var volumeIconVisibility by remember { mutableStateOf(false) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory,
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))

                setMediaSource(source)
                prepare()
            }
    }

    exoPlayer.volume = if (isMuted) 0f else 1f
    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

    Box {
        DisposableEffect(
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        hideController()
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    }
                },
                modifier = Modifier
                    .pointerInput(isMuted) {
                        detectTapGestures(
                            onTap = {
                                if (exoPlayer.playWhenReady) {
                                    val (volume, muted) = if (isMuted.not()) Pair(0f, true) else Pair(1f, false)
                                    exoPlayer.volume = volume
                                    onMuted(muted)

                                    coroutineScope.launch {
                                        volumeIconVisibility = true
                                        delay(800)
                                        volumeIconVisibility = false
                                    }
                                }
                            },
                            onPress = {
//                                if (!isScrolling) {
//                                    exoPlayer.playWhenReady = false
//                                    awaitRelease()
//                                    exoPlayer.playWhenReady = true
//                                }
                            },
                        )
                    },
            ),
        ) {
            onDispose { exoPlayer.release() }
        }

        AnimatedVisibility(
            visible = volumeIconVisibility,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy),
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center),
        ) {
            Icon(
                painter = painterResource(id = if (isMuted) R.drawable.ic_volume_off else R.drawable.ic_volume_on),
                contentDescription = null,
                tint = Color.White.copy(0.75f),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp),
            )
        }
    }
}
