package com.core.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.core.ui.R

@Composable
fun Dialog(
    dialogData: DialogData,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        title = { },
        text = {
            Text(dialogData.description.asString())
        },
        confirmButton = { },
        dismissButton = {
            Button(
                onClick = onDismiss,
            ) {
                Text(stringResource(id = R.string.close))
            }
        },
    )
}
