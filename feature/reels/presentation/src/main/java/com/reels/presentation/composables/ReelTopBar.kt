package com.reels.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReelTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
            )
            Text(
                text = "News Room",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.padding(start = 12.dp),
            )
        }

        Box {
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "This is a profile picture",
//                contentScale = ContentScale.Fit,
//                modifier = Modifier
//                    .size(55.dp)
//                    .clip(CircleShape),
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReelTopBarPreview() {
    ReelTopBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
}
