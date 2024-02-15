package com.artemissoftware.feature.details.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.newsroom.core.common.R
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = MaterialTheme.palette.extraColors.body,
            navigationIconContentColor = MaterialTheme.palette.extraColors.body,
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onBookMarkClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null,
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailsTopBarPreview() {
    NewsRoomTheme {
        DetailsTopBar(
            onShareClick = { /*TODO*/ },
            onBookMarkClick = { /*TODO*/ },
            onBrowsingClick = {},
            onBackClick = {},
        )
    }
}
