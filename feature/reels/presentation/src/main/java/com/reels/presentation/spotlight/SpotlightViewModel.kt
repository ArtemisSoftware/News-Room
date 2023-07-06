package com.reels.presentation.spotlight

import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpotlightViewModel @Inject constructor(
    // private val savedStateHandle: SavedStateHandle,
    val player: Player,
    // private val metaDataReader: MetaDataReader
) : ViewModel(){

    init {
        player.playWhenReady = true
    }

}
