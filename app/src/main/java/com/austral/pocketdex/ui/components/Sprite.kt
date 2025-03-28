package com.austral.pocketdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.austral.pocketdex.R

@Composable
fun Sprite(
    id: Int,
    modifier: Modifier = Modifier,
    hidden: Boolean = false
) {
    AsyncImage(
        model = stringResource(R.string.sprite_url, id),
        contentDescription = "sprite of pokemon #$id",
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Transparent),
        colorFilter = if (hidden) ColorFilter.tint(Color.Black) else null
    )
}