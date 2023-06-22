package com.reels.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reels.presentation.R

@Composable
fun Header(
    title: String,
    modifier: Modifier = Modifier,
    isFirstItem: Boolean,
) {
    Box(
        modifier = modifier
            .padding(PaddingValues(8.dp, 16.dp)),
    ) {
        AnimatedVisibility(
            visible = isFirstItem,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.baseline_360_24),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(30.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
private fun HeaderPreview() {
    Header(modifier = Modifier.fillMaxWidth(), title = "title", isFirstItem = true)
}
