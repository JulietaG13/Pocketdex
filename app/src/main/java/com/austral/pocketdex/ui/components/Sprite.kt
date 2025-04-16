package com.austral.pocketdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.austral.pocketdex.R
import com.austral.pocketdex.data.model.Pokemon

@Composable
fun Sprite(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    hidden: Boolean = false
) {

    val scale = when {
        pokemon.height > 15 -> 0.85f
        pokemon.height > 9 -> 0.9f
        else -> 1.0f
    }

    AsyncImage(
        model = stringResource(R.string.sprite_url, pokemon.id),
        contentDescription = stringResource(R.string.sprite_image_content_description, pokemon.id),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(Color.Transparent),
        colorFilter = if (hidden) ColorFilter.tint(Color.Black) else null
    )
}