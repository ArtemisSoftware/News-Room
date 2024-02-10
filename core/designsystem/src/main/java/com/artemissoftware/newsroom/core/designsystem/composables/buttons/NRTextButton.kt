package com.artemissoftware.newsroom.core.designsystem.composables.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing

@Composable
fun NRTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.palette.extraColors.OverlayWhite,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NRButtonPreview() {
    NewsRoomTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2),
        ) {
            NRTextButton(
                text = "Title",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            )
        }
    }
}
