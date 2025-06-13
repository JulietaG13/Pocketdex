package com.austral.pocketdex.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.austral.pocketdex.data.model.Pokemon

@Composable
fun PokeListItem(
    pokemon: Pokemon,
    found: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Sprite(
            pokemon = pokemon,
            hidden = !found
        )
    }
}