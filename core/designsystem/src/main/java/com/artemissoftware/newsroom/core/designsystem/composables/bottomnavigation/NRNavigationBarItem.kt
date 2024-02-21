package com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.composables.PreviewData
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension
import com.artemissoftware.newsroom.core.designsystem.theme.palette

@Composable
internal fun RowScope.NRNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5),
//    ) {
//        Icon(
//            painter = painterResource(id = item.icon),
//            contentDescription = null,
//            modifier = Modifier.size(MaterialTheme.dimension.iconSize),
//        )
//        Text(
//            text = stringResource(id = item.text),
//            style = MaterialTheme.typography.labelSmall,
//        )
//    }

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.palette.extraColors.body,
            unselectedTextColor = MaterialTheme.palette.extraColors.body,
            indicatorColor = MaterialTheme.colorScheme.background,
        ),
    )
}

@ThemePreviews
@Composable
private fun NavigationItemPreview() {
    NewsRoomTheme {
        Row {
            NRNavigationBarItem(
                selected = true,
                onClick = {},
                icon = {
                    Icon(
                        painter = painterResource(id = PreviewData.bottomNavigationItem.icon),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.dimension.iconSize),
                    )
                },
            )
        }
    }
}
