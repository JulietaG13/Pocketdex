package com.austral.pocketdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.ui.theme.Dimensions

@Composable
fun PokeCardDialog(
    pokemon: Pokemon,
    onDismiss: () -> Unit
) {

    val containerColor = pokemon.type[0].color.copy(alpha = 0.2f)

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                shape = RoundedCornerShape(Dimensions.RoundedCorner),
                colors = CardDefaults.cardColors(containerColor = containerColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
                    .clickable(onClick = onDismiss)
                    .shadow(8.dp, RoundedCornerShape(Dimensions.RoundedCorner))
                    .border(
                        width = 14.dp,
                        brush = shimmerBorderBrush(),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .border(
                        width = 14.dp,
                        brush = typeBorderBrush(pokemon.type),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .background(color = Color.White)
                    .padding(Dimensions.MediumPadding),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimensions.LargePadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Pokemon name
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .zIndex(1f),
                        text = pokemon.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        textAlign = TextAlign.Left
                    )

                    Spacer(modifier = Modifier
                        .height(Dimensions.MediumPadding)
                        .fillMaxWidth()
                        .zIndex(1f)
                    )

                    // Pokemon box
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.8f)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                    ) {
                        Column {
                            Spacer(
                                modifier = Modifier
                                    .height(Dimensions.SmallSpacer)
                            )

                            Sprite(
                                pokemon = pokemon,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .zIndex(-1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimensions.MediumSpacer))

                    // Type badges
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        pokemon.type.forEach { type ->
                            PokemonTypeBadge(type)
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimensions.MediumPadding))

                    // Description
                    Text(
                        text = pokemon.description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = Dimensions.MediumPadding)
                    )
                }
            }
        }
    }
}