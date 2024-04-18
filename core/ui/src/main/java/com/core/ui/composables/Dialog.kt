package com.core.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Dialog(
    showDialog: Boolean,
    dialogData: DialogData,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = "Dialog Title Will Show Here")
            },
            text = {
                Text(dialogData.description.asString())
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
