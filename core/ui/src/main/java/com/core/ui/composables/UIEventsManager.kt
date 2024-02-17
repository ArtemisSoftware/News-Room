package com.core.ui.composables

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.core.ui.uievents.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UIEventsManager(
    uiEvent: Flow<UiEvent>,
    context: Context = LocalContext.current,
) {
    LaunchedEffect(key1 = Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, event.uiText.asString(context), event.duration).show()
                }

                is UiEvent.OpenWebBrowser -> {
                    Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(event.url)
                    }
                        .also { intent ->
                            try {
                                context.startActivity(intent)
                            } catch (ex: ActivityNotFoundException) {
                                // --showToast(R.string.application_not_found)
                            }
                        }
// //-------
//
//
//                                    Intent(Intent.ACTION_VIEW).also {
//
//
//                                        try {
//                                            startActivity(intent)
//                                        } catch (ex: ActivityNotFoundException) {
//                                            showToast(R.string.application_not_found)
//                                        }
//
//                    it.data = Uri.parse(event.url)
//                    if (it.resolveActivity(context.packageManager) != null) {
//                        context.startActivity(it)
//                    }
//
//
//                }
                }
                is UiEvent.Share -> {

                    Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, event.url)
                    }
                        .also { intent ->
                            try {
                                context.startActivity(intent)
                            } catch (ex: ActivityNotFoundException) {
                                // --showToast(R.string.application_not_found)
                            }
                        }
                    //---------

                }
            }
        }
    }
}
