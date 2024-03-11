package com.artemissoftware.newsroom.util.extensions

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.artemissoftware.newsroom.core.designsystem.composables.bottomnavigation.TopLevelDestination

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false

fun NavDestination?.getTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false
