package com.austral.pocketdex.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.austral.pocketdex.ui.theme.LocalExtendedColors

@Composable
fun MovingDiagonalBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    val color = LocalExtendedColors.current.lightPrimary

    val strokeWidth = 90f
    val lineSpacing = strokeWidth * 3

    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = lineSpacing,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val extraOffset = size.height

        for (i in -extraOffset.toInt()..(size.width + extraOffset).toInt() step lineSpacing.toInt()) {
            drawLine(
                color = color,
                start = androidx.compose.ui.geometry.Offset(i + offsetX + extraOffset, -extraOffset),
                end = androidx.compose.ui.geometry.Offset(i + offsetX - extraOffset, size.height + extraOffset),
                strokeWidth = strokeWidth
            )
        }
    }
}
