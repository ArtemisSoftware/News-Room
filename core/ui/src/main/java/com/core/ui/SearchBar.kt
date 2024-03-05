package com.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isSearching: Boolean = false,
    historyItems: List<String> = emptyList(),
) {
    SearchBar(
        modifier = modifier,
        query = text,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isSearching,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.palette.background,
        ),
        onActiveChange = onActiveChange,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.bodySmall,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
            )
        },
    ) {
        historyItems.forEach { historyItem ->
            Row(modifier = Modifier.padding(all = MaterialTheme.spacing.spacing2)) {
                Icon(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.spacing1_5),
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
                Text(
                    text = historyItem,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun SearchBarPreview() {
    NewsRoomTheme {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            text = "",
            onQueryChange = { },
            onSearch = { },
            onActiveChange = { },
        )
    }
}

@ThemePreviews
@Composable
private fun SearchBar_history_Preview() {
    NewsRoomTheme {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            historyItems = listOf("Theme 1", "Theme 2"),
            text = "Theme 3",
            isSearching = true,
            onQueryChange = { },
            onSearch = { },
            onActiveChange = { },
        )
    }
}
