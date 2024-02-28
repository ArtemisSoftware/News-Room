package com.core.ui.navigation

import androidx.navigation.NavType

data class CustomArguments(
    val key: String,
    val type: NavType<*> = NavType.StringType,
    val nullable: Boolean = true
)
