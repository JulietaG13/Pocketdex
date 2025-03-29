package com.austral.pocketdex.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import com.austral.pocketdex.data.model.PokemonType

@Composable
fun typeBorderBrush(
    types: List<PokemonType>
): Brush {

    val gradientStops = remember(types) {
        val stops = mutableListOf<Pair<Float, Color>>()
        val typeColors = types.map { it.color }

        if (typeColors.isNotEmpty()) {
            val step = 1f / (typeColors.size * 2)

            for (i in typeColors.indices) {
                val position = i * step * 2
                stops.add(position to typeColors[i])
                stops.add(position + step to Color.White)
            }
            stops.add(1f to typeColors.last())
        }
        stops
    }

    return Brush.linearGradient(
        colorStops = gradientStops.toTypedArray(),
        start = Offset.Zero,
        end = Offset.Infinite,
        tileMode = TileMode.Repeated
    )
}