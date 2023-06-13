package com.news.presentation.news.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.news.presentation.news.models.Topic

@Composable
fun TopicChip(
    topic: Topic,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    showTitle: Boolean = true,
) {
    val padding by animateDpAsState(targetValue = getPadding(isSelected))
    val colors by animateColorAsState(targetValue = getColors(isSelected = isSelected))

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(getHeight(isSelected = isSelected))
                .shadow(
                    elevation = getElevation(isSelected = isSelected),
                    shape = RoundedCornerShape(20.dp),
                )
                .background(
                    color = colors,
                    shape = RoundedCornerShape(20.dp),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = rememberVectorPainter(
                    image = getImage(isSelected = isSelected, topic = topic),
                ),
                contentDescription = topic.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .padding(start = padding)
                    .alpha(getAlpha(isSelected = isSelected))
                    .size(getSize(isSelected = isSelected)),
            )

            if (showTitle) {
                Text(
                    text = topic.title,
                    modifier = Modifier.padding(start = 8.dp, end = 12.dp),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun getColors(isSelected: Boolean) = if (isSelected) MaterialTheme.colorScheme.surface else Color.LightGray
private fun getHeight(isSelected: Boolean) = if (isSelected) 36.dp else 26.dp
private fun getPadding(isSelected: Boolean) = if (isSelected) 12.dp else 2.dp
private fun getElevation(isSelected: Boolean) = if (isSelected) 15.dp else 0.dp
private fun getImage(isSelected: Boolean, topic: Topic) = if (isSelected) topic.activeIcon else topic.inactiveIcon
private fun getAlpha(isSelected: Boolean) = if (isSelected) 1f else .5f
private fun getSize(isSelected: Boolean) = if (isSelected) 26.dp else 20.dp

@Preview(showBackground = true)
@Composable
private fun TopicChipPreview() {
    Column {
        TopicChip(
            topic = Topic.Home,
            isSelected = false,
        )
        TopicChip(
            topic = Topic.Home,
            isSelected = true,
        )
    }
}
