package com.austral.pocketdex.ui.components

import android.content.Context
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
import androidx.compose.ui.text.font.FontWeight
import com.austral.pocketdex.data.model.PokemonType
import com.austral.pocketdex.ui.theme.Dimensions
import java.util.Locale

@Composable
fun PokemonTypeBadge(
    context: Context,
    type: PokemonType
) {
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