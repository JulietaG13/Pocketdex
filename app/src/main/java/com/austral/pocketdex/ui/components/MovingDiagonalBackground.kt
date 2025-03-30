package com.austral.pocketdex.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
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
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                renderEffect = BlurEffect(
                    radiusX = 20f,
                    radiusY = 20f,
                    edgeTreatment = TileMode.Repeated
                )
            }
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
