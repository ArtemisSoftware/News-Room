package com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.composables.PreviewData
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension

@Composable
fun NRNavigationBar(
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

            NRNavigationBarItem(
                selected = /*selected*/true,
                onClick = {
                    // onNavigateToDestination(destination)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = Color.Cyan,
                        modifier = Modifier.size(MaterialTheme.dimension.iconSize),
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(MaterialTheme.dimension.iconSize),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.text),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                // modifier = if (hasUnread) Modifier.notificationDot() else Modifier,
            )

//            NavigationBarItem(
//                selected = index == selectedItem,
//                onClick = { onItemClick(index) },
//                icon = {
//                    NavigationItem(item = item)
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.primary,
//                    selectedTextColor = MaterialTheme.colorScheme.primary,
//                    unselectedIconColor = MaterialTheme.palette.extraColors.body,
//                    unselectedTextColor = MaterialTheme.palette.extraColors.body,
//                    indicatorColor = MaterialTheme.colorScheme.background,
//                ),
//            )
        }
    }
}

@ThemePreviews
@Composable
private fun BottomNavigationBarPreview() {
    NewsRoomTheme {
        NRNavigationBar(
            items = PreviewData.listBottomNavigationItem,
            selectedItem = 0,
            onItemClick = {},
        )
    }
}
