package com.core.ui.composables.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.core.ui.composables.PreviewData

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    NavigationItem(item = item)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.palette.extraColors.body,
                    unselectedTextColor = MaterialTheme.palette.extraColors.body,
                    indicatorColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    }
}

@ThemePreviews
@Composable
private fun BottomNavigationBarPreview() {
    NewsRoomTheme {
        BottomNavigationBar(
            items = PreviewData.listBottomNavigationItem,
            selectedItem = 0,
            onItemClick = {},
        )
    }
}
