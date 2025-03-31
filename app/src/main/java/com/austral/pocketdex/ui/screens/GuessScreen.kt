package com.austral.pocketdex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.austral.pocketdex.ui.components.Sprite
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.util.MockData
import com.austral.pocketdex.util.MockPokemonApi
import kotlin.random.Random

@Composable
fun GuessScreen() {
    var guess by remember { mutableStateOf("") }
    var id by remember { mutableIntStateOf(MockData.pokemonList.random().id /*Random.nextInt(1, 1025 + 1)*/) }
    var triesLeft by remember { mutableIntStateOf(3) }
    var revealedHints by remember { mutableStateOf(emptyList<String>()) }
    var showResult by remember { mutableStateOf(false) }

    val pokemon = MockPokemonApi().getPokemonById(id)   // TODO

    val clues = listOf("It's a Water-type!", "It evolves at level 16.") // TODO

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.LargePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding)
    ) {

        // hidden sprite
        val circleColor = MaterialTheme.colorScheme.surfaceVariant
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val gradient = Brush.radialGradient(
                        0.9f to circleColor,
                        1f to Color.Transparent,
                        center = center,
                        radius = (size.minDimension / 2f)
                    )
                    drawCircle(brush = gradient, radius = size.minDimension / 2f, center = center)
                },
        ) {

            Sprite(
                pokemon = pokemon!!,
                hidden = !showResult
            )
        }

        // input field
        TextField(
            value = guess,
            onValueChange = { guess = it },
            placeholder = {
                Text(
                    "Who's that Pokémon?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.MediumPadding)
                .clip(RoundedCornerShape(Dimensions.SmallRoundedCorner)),

            shape = RoundedCornerShape(Dimensions.SmallRoundedCorner),

            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        // submit
        Button(
            onClick = {
                if (guess.isNotBlank() && triesLeft > 0) {
                    if (guess.lowercase() == pokemon!!.name.lowercase()) {
                        showResult = true
                    } else {
                        triesLeft--
                        if (triesLeft <= 2 && revealedHints.size < clues.size) {
                            revealedHints = revealedHints + clues[revealedHints.size]
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text("Submit")
        }

        // attempts left
        Text(
            text = "Attempts left: $triesLeft",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        // clues
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(revealedHints, key = { it.length }) { hint ->
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(top = Dimensions.SmallPadding)
                        .animateItem()
                )
            }
        }

        // result
        if (showResult) {
            Text(
                text = "Correct! It's ${pokemon!!.name}!",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
