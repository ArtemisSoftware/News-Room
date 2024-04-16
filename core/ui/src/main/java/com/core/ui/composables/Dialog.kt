package com.core.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Dialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = "Dialog Title Will Show Here")
            },
            text = {
                Text("Here is a description text of the dialog")
            },
            confirmButton = { },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                ) {
                    Text("Dismiss Button")
                }
            },
        )
    }
}
