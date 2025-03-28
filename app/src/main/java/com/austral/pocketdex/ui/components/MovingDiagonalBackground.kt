package com.austral.pocketdex.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MovingDiagonalBackground() {
    val infiniteTransition = rememberInfiniteTransition()

    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val strokeWidth = 80f
        val lineSpacing = strokeWidth * 4
        val extraOffset = size.height

        for (i in -extraOffset.toInt()..(size.width + extraOffset).toInt() step lineSpacing.toInt()) {
            drawLine(
                color = Color(red = 200, green = 200, blue = 200, alpha = 100),
                start = androidx.compose.ui.geometry.Offset(i + offsetX + extraOffset, -extraOffset),
                end = androidx.compose.ui.geometry.Offset(i + offsetX - extraOffset, size.height + extraOffset),
                strokeWidth = strokeWidth
            )
        }
    }
}
