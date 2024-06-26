package com.artemissoftware.newsroom.core.designsystem.composables.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.shape

@Composable
fun NRButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.palette.primary,
            contentColor = MaterialTheme.palette.onPrimary,
        ),
        shape = MaterialTheme.shape.button,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NRButtonPreview() {
    NewsRoomTheme {
        NRButton(
            text = "Title",
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}
