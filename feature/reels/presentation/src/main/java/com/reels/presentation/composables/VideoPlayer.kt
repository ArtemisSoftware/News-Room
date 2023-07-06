package com.reels.presentation.composables

import android.net.Uri
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.reels.presentation.R
import com.reels.presentation.spotlight.Reel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VideoPlayer_(
    reel: Reel,
    shouldPlay: Boolean,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
    isScrolling: Boolean,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    // val exoPlayer = rememberExoPlayerWithLifecycle(reel.reelUrl)

    val context = LocalContext.current
    val exoPlayer = remember(reel.reelUrl) {
        ExoPlayer.Builder(context).build().apply {
            //videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            repeatMode = Player.REPEAT_MODE_ONE

            setHandleAudioBecomingNoisy(true)
            val defaultDataSource = DefaultHttpDataSource.Factory()
            val source = ProgressiveMediaSource.Factory(defaultDataSource)
                .createMediaSource(MediaItem.fromUri(reel.reelUrl))
            setMediaSource(source)
            prepare()
        }
    }

    // val playerView = rememberPlayerView(exoPlayer)

    var volumeIconVisibility by remember { mutableStateOf(false) }
    var likeIconVisibility by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val videoModifier = modifier
/*
    val videoModifier = if (isExpanded) {
        Modifier
//            .pointerInput(
//                isMuted,
////                    key1 = reel.reelInfo.isLiked,
////                    key2 = isMuted
//            ) {
//                detectTapGestures(
//                    onTap = {
//                        if (exoPlayer.playWhenReady) {
//                            if (isMuted.not()) {
//                                exoPlayer.volume = 0f
//                                onMuted(true)
//                            } else {
//                                exoPlayer.volume = 1f
//                                onMuted(false)
//                            }
//                            coroutineScope.launch {
//                                volumeIconVisibility = true
//                                delay(800)
//                                volumeIconVisibility = false
//                            }
//                        }
//                    },
//                    onPress = {
//                        if (!isScrolling) {
//                            exoPlayer.playWhenReady = false
//                            awaitRelease()
//                            exoPlayer.playWhenReady = true
//                        }
//                    },
//                    onLongPress = {},
//                )
//            }
    } else {
        Modifier
    }
*/
    Box {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                }
                // playerView
            },
            modifier = videoModifier,
            update = {
                exoPlayer.volume = if (isMuted) 0f else 1f
                exoPlayer.playWhenReady = shouldPlay
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

    DisposableEffect(key1 = true) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReelPlayer(
    reel: Reel,
    shouldPlay: Boolean,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
    onDoubleTap: (Boolean) -> Unit,
    isScrolling: Boolean,
) {
    val exoPlayer = rememberExoPlayerWithLifecycle(reel.reelUrl)
    val playerView = rememberPlayerView(exoPlayer)
    var volumeIconVisibility by remember { mutableStateOf(false) }
    var likeIconVisibility by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box {
        AndroidView(
            factory = { playerView },
            modifier = Modifier
                .pointerInput(reel.reelInfo.isLiked, isMuted) {
                    detectTapGestures(
                        onDoubleTap = {
                            onDoubleTap(true)
                            coroutineScope.launch {
                                likeIconVisibility = true
                                delay(800)
                                likeIconVisibility = false
                            }
                        },
                        onTap = {
                            if (exoPlayer.playWhenReady) {
                                if (isMuted.not()) {
                                    exoPlayer.volume = 0f
                                    onMuted(true)
                                } else {
                                    exoPlayer.volume = 1f
                                    onMuted(false)
                                }
                                coroutineScope.launch {
                                    volumeIconVisibility = true
                                    delay(800)
                                    volumeIconVisibility = false
                                }
                            }
                        },
                        onPress = {
                            if (!isScrolling) {
                                exoPlayer.playWhenReady = false
                                awaitRelease()
                                exoPlayer.playWhenReady = true
                            }
                        },
                        onLongPress = {},
                    )
                },
            update = {
                exoPlayer.volume = if (isMuted) 0f else 1f
                exoPlayer.playWhenReady = shouldPlay
            },
        )

        AnimatedVisibility(
            visible = likeIconVisibility,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy),
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.White.copy(0.90f),
                modifier = Modifier
                    .size(100.dp),
            )
        }

        if (volumeIconVisibility) {
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

    DisposableEffect(key1 = true) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Composable
fun rememberExoPlayerWithLifecycle(
    reelUrl: String,
): ExoPlayer {
    val context = LocalContext.current
    val exoPlayer = remember(reelUrl) {
        ExoPlayer.Builder(context).build().apply {
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            repeatMode = Player.REPEAT_MODE_ONE
            setHandleAudioBecomingNoisy(true)
            val defaultDataSource = DefaultHttpDataSource.Factory()
            val source = ProgressiveMediaSource.Factory(defaultDataSource)
                .createMediaSource(MediaItem.fromUri(reelUrl))
            setMediaSource(source)
            prepare()
        }
    }
    var appInBackground by remember {
        mutableStateOf(false)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, appInBackground) {
        val lifecycleObserver = getExoPlayerLifecycleObserver(exoPlayer, appInBackground) {
            appInBackground = it
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
    return exoPlayer
}

@Composable
fun rememberPlayerView(exoPlayer: ExoPlayer): PlayerView {
    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            player = exoPlayer
            setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)
        }
    }
    DisposableEffect(key1 = true) {
        onDispose {
            playerView.player = null
        }
    }
    return playerView
}

fun getExoPlayerLifecycleObserver(
    exoPlayer: ExoPlayer,
    wasAppInBackground: Boolean,
    setWasAppInBackground: (Boolean) -> Unit,
): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                if (wasAppInBackground) {
                    exoPlayer.playWhenReady = true
                }
                setWasAppInBackground(false)
            }
            Lifecycle.Event.ON_PAUSE -> {
                exoPlayer.playWhenReady = false
                setWasAppInBackground(true)
            }
            Lifecycle.Event.ON_STOP -> {
                exoPlayer.playWhenReady = false
                setWasAppInBackground(true)
            }
            Lifecycle.Event.ON_DESTROY -> {
                exoPlayer.release()
            }
            else -> {}
        }
    }

// @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun VideoPlayer(
    uri: Uri,
    shouldPlay: Boolean,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
    isScrolling: Boolean,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var volumeIconVisibility by remember { mutableStateOf(false) }
    var istap by remember { mutableStateOf(true) }

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
    exoPlayer.playWhenReady = shouldPlay
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
                                Toast.makeText(context, "_onTap exoPlayer.playWhenReady ${exoPlayer.playWhenReady}", Toast.LENGTH_SHORT).show()

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
                                istap = true
                            },
                            onPress = {
                                if (!isScrolling) {
                                    Toast.makeText(context, "_onPress $istap", Toast.LENGTH_SHORT).show()

                                    exoPlayer.playWhenReady = false
                                    awaitRelease()
                                    istap = true
                                    exoPlayer.playWhenReady = true
                                }
                            },
                        )
                    },
                update = {
                    exoPlayer.volume = if (isMuted) 0f else 1f
                    exoPlayer.playWhenReady = shouldPlay
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
