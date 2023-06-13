package com.news.presentation.news.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.news.presentation.news.models.Topic

@Composable
fun NewsHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Today's date",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            color = Color.Gray,
        )

        Text(
            text = "Task-based \nExplanation Process",
            modifier = Modifier.padding(bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium,
        )

        TopicSelector(listOf(Topic.Home, Topic.Settings, Topic.Profile))
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsHeaderPreview() {
    NewsHeader(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
}
