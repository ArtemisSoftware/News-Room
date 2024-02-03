package com.artemissoftware.newsroom.core.designsystem

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "1- Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "2 -Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ThemePreviews
