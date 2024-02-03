package com.artemissoftware.newsroom.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shaping(
    val button: Shape,
)

internal val shape = Shaping(
    button = RoundedCornerShape(size = 6.dp),
)

internal val localShape = staticCompositionLocalOf<Shaping> { throw IllegalStateException("No theme installed") }
