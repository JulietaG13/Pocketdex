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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.austral.pocketdex.ui.theme.LocalExtendedColors
import com.austral.pocketdex.ui.theme.PocketdexTheme

@Composable
fun MovingDiagonalBackground(modifier: Modifier = Modifier) {
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
        modifier = modifier
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
                start = Offset(i + offsetX + extraOffset, -extraOffset),
                end = Offset(i + offsetX - extraOffset, size.height + extraOffset),
                strokeWidth = strokeWidth
            )
        }
    }
}

@Preview(showBackground = true)
@PreviewScreenSizes
@Composable
private fun MovingDiagonalBackgroundPreview() {
    PocketdexTheme {
        MovingDiagonalBackground()
    }
}