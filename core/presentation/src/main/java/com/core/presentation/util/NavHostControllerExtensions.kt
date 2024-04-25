package com.core.presentation.util

import androidx.navigation.NavHostController

fun NavHostController.popUpTo(fromRoute: String, toRoute: String) {
    this.navigate(toRoute) {
        popUpTo(fromRoute) { inclusive = true }
    }
}
