package com.austral.pocketdex.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun shimmerBorderBrush(): Brush {

    val infiniteTransition = rememberInfiniteTransition()
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val colors = listOf(
        0.0f + shimmerOffset to Color.Transparent,
        0.1f + shimmerOffset to Color.White.copy(alpha = 0.8f),
        0.2f + shimmerOffset to Color.Transparent,
        0.3f + shimmerOffset to Color.White.copy(alpha = 0.8f),
        0.4f + shimmerOffset to Color.Transparent,
    )

    return Brush.linearGradient(
        colorStops = colors.toTypedArray(),
        start = Offset.Zero,
        end = Offset.Infinite
    )
}
