package com.core.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val extraSmallPadding: Dp,
    val smallPadding: Dp,
    val mediumPadding: Dp,
    val mediumLargePadding: Dp,
    val mediumExtraLargePadding: Dp,
)

val spacing = Spacing(
    extraSmallPadding = 2.dp,
    smallPadding = 4.dp,
    mediumPadding = 24.dp,
    mediumLargePadding = 32.dp,
    mediumExtraLargePadding = 40.dp,
)
