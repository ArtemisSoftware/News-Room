package com.news.presentation.news.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed class Screen(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
) {
    object Home : Screen("Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Create : Screen("Create", Icons.Filled.Create, Icons.Outlined.Create)
    object Profile : Screen("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    object Settings : Screen("Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}

@Composable
fun TopicSelector(
    screens: List<Screen>,
    selectedTopicIndex: Int = 0,
) {
    var selectedScreen by remember { mutableStateOf(0) }
    Box(
        Modifier
            // .shadow(5.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .height(40.dp)
            .fillMaxWidth()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(24.dp),
            )
            .clip(RoundedCornerShape(24.dp))
            .padding(horizontal = 2.dp)
        ,
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            for (screen in screens) {
                val isSelected = screen == screens[selectedScreen]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)
                val animated_Weight by animateDpAsState(targetValue = if (isSelected) 20.dp else 0.dp)
                Box(
                    modifier = Modifier.weight(1F),
                    //modifier = Modifier.padding(start = animated_Weight, end = animated_Weight),
                    // modifier = Modifier.padding(start = 0.dp, end = animated_Weight),
                    // modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Alignment.Center,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    TopicChip(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            selectedScreen = screens.indexOf(screen)
                        },
                        screen = screen,
                        isSelected = isSelected,
                    )
                }
            }
        }
    }

//    var sizes by remember {
//        mutableStateOf(mutableListOf(IntSize.Zero, IntSize.Zero, IntSize.Zero))
//    }
//
//    var selectedScreen by remember { mutableStateOf(0) }
//
//    Box(
//        modifier = Modifier
//            //            .shadow(5.dp)
//                        .background(color = MaterialTheme.colorScheme.surface)
// //                        .height(64.dp)
//            .fillMaxWidth(),
//    ) {
//        Row(
//            Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            screens.forEachIndexed { index, screen ->
//
//                val isSelected = screen == screens[selectedTopicIndex]
//                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)
//
//                Box(
//                    modifier = Modifier.weight(animatedWeight),
// //                contentAlignment = Alignment.Center,
//                ) {
//                    val interactionSource = remember { MutableInteractionSource() }
//                    TopicChip(
//                        modifier = Modifier
//                            .clickable(
//                                interactionSource = interactionSource,
//                                indication = null,
//                                onClick = {
//                                    selectedScreen = screens.indexOf(screen)
//                                },
//                            ),
//                        screen = screen,
//                        isSelected = isSelected,
//                    )
//                }
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
private fun TopicSelectorPreview() {
    TopicSelector(
        screens = listOf(Screen.Home, Screen.Settings, Screen.Profile),
    )
}

@Composable
fun BottomNavNoAnimation(
    screens: List<Screen>,
) {
    var selectedScreen by remember { mutableStateOf(0) }
    Box(
        Modifier
            // .shadow(5.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .height(64.dp)
            .fillMaxWidth(),
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (screen in screens) {
                val isSelected = screen == screens[selectedScreen]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)
                Box(
                    modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Alignment.Center,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    BottomNavItem(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            selectedScreen = screens.indexOf(screen)
                        },
                        screen = screen,
                        isSelected = isSelected,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavNoAnimationPreview() {
    BottomNavNoAnimation(listOf(Screen.Home, Screen.Settings, Screen.Profile))
}

@Composable
private fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: Screen,
    isSelected: Boolean,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(if (isSelected) 36.dp else 26.dp)
                .shadow(
                    elevation = if (isSelected) 15.dp else 0.dp,
                    shape = RoundedCornerShape(20.dp),
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                rememberVectorPainter(
                    image = if (isSelected) screen.activeIcon else screen.inactiveIcon,
                ),
                contentDescription = screen.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .padding(start = 11.dp)
                    .alpha(if (isSelected) 1f else .5f)
                    .size(if (isSelected) 26.dp else 20.dp),
            )

            if (true) {
                Text(
                    text = screen.title,
                    modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                    maxLines = 1,
                )
            }
        }
    }
}
