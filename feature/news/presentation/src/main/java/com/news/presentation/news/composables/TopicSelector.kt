package com.news.presentation.news.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.news.presentation.news.models.Topic

@Composable
fun TopicSelector(
    topics: List<Topic>,
    modifier: Modifier = Modifier,
    selectedTopicIndex: Int = 0,
) {
    var selectedScreen by remember { mutableStateOf(0) }
    Box(
        modifier
            .height(40.dp)
            .clip(RoundedCornerShape(24.dp))
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(24.dp),
            )
            .padding(horizontal = 2.dp),
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            for (topic in topics) {
                val isSelected = topic == topics[selectedScreen]
                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    TopicChip(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            selectedScreen = topics.indexOf(topic)
                        },
                        topic = topic,
                        isSelected = isSelected,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TopicSelectorPreview() {
    TopicSelector(
        modifier = Modifier.fillMaxWidth(),
        topics = listOf(Topic.Home, Topic.Settings, Topic.Profile),
    )
}
