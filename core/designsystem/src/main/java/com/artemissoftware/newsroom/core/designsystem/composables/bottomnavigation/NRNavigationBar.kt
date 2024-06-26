package com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.composables.PreviewData
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.dimension

@Composable
fun NRNavigationBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
) {
    currentDestination?.let {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 1.dp,
        ) {
            destinations.forEachIndexed { index, destination ->

                NRNavigationBarItem(
                    selected = currentDestination == destination,
                    onClick = {
                        onNavigateToDestination(destination)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = destination.icon),
                            contentDescription = null,
                            modifier = Modifier.size(MaterialTheme.dimension.iconSize),
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = destination.icon),
                            contentDescription = null,
                            modifier = Modifier.size(MaterialTheme.dimension.iconSize),
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destination.text),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun BottomNavigationBarPreview() {
    NewsRoomTheme {
        NRNavigationBar(
            destinations = PreviewData.listBottomNavigationItem,
            currentDestination = PreviewData.destinationHome,
            onNavigateToDestination = {},
        )
    }
}
