package com.austral.pocketdex.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.austral.pocketdex.ui.theme.ListItemBackground

@Composable
fun PokeListItem(
    id: Int,
    found: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = ListItemBackground),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        if (found) {
            Sprite(id)
        } else {
            Text(text = id.toString())
        }
    }
}