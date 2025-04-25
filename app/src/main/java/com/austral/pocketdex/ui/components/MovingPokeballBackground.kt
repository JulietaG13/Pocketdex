package com.austral.pocketdex.ui.components

import android.graphics.BitmapFactory
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.austral.pocketdex.R
import com.austral.pocketdex.ui.theme.PocketdexTheme

@Composable
fun MovingPokeballBackground() {
    val context = LocalContext.current
    val pokeballBitmap = remember {
        BitmapFactory.decodeResource(context.resources, R.drawable.pokeball)
    }

    val scaleFactor = 2.6f
    val spacingFactor = 2.4f

    val scaledWidth = (pokeballBitmap.width * scaleFactor).toInt()
    val scaledHeight = (pokeballBitmap.height * scaleFactor).toInt()

    val animationOffset by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = scaledWidth.toFloat() * spacingFactor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 16000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val stepX = (scaledWidth * spacingFactor).toInt()
        val stepY = (scaledHeight * spacingFactor).toInt()
        val halfStepX = stepX / 2

        for ((rowIndex, y) in (-stepY..size.height.toInt() + stepY step stepY).withIndex()) {
            val rowOffsetX = if (rowIndex % 2 == 0) 0 else halfStepX

            for (x in -stepX..size.width.toInt() + stepX step stepX) {
                drawIntoCanvas { canvas ->
                    val matrix = android.graphics.Matrix().apply {
                        postScale(scaleFactor, scaleFactor)
                        postTranslate(
                            (x + rowOffsetX + (animationOffset % stepX)),
                            (y + (animationOffset % stepY))
                        )
                    }
                    canvas.nativeCanvas.drawBitmap(pokeballBitmap, matrix, null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@PreviewScreenSizes
@Composable
private fun MovingPokeballBackgroundPreview() {
    PocketdexTheme {
        MovingPokeballBackground()
    }
}