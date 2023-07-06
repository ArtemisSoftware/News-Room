package com.reels.presentation.spotlight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SpotlightSection(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = "reel.userName",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = "reel.userName",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
private fun SpotlightSectionPreview() {
    SpotlightSection(modifier = Modifier.fillMaxSize())
}
