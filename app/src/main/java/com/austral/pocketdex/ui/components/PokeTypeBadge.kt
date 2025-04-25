package com.austral.pocketdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.austral.pocketdex.data.model.PokemonType
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.ui.theme.PocketdexTheme
import java.util.Locale

@Composable
fun PokemonTypeBadge(
    type: PokemonType
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(Dimensions.SmallPadding)
            .background(type.color, shape = RoundedCornerShape(Dimensions.MediumRoundedCorner))
            .padding(
                horizontal = Dimensions.TypeBadgeHorizontalPadding,
                vertical = Dimensions.TypeBadgeVerticalPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.getLocalizedName(context).uppercase(Locale.ROOT),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypeBadgeFairyPreview() {
    PocketdexTheme {
        PokemonTypeBadge(
            type = PokemonType.FAIRY
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypeBadgeDragonPreview() {
    PocketdexTheme {
        PokemonTypeBadge(
            type = PokemonType.DRAGON
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypeBadgeFirePreview() {
    PocketdexTheme {
        PokemonTypeBadge(
            type = PokemonType.FIRE
        )
    }
}