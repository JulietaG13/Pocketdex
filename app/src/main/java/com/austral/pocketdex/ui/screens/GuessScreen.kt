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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.austral.pocketdex.R
import com.austral.pocketdex.ui.components.Sprite
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.viewmodel.GuessViewModel

@Composable
fun GuessScreen(viewModel: GuessViewModel = hiltViewModel<GuessViewModel>()) {
    val guess by viewModel.guess.collectAsState()
    val pokemon by viewModel.pokemon.collectAsState()
    val triesLeft by viewModel.triesLeft.collectAsState()
    val revealedHints by viewModel.revealedHints.collectAsState()
    val showResult by viewModel.showResult.collectAsState()

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
                pokemon = pokemon,
                hidden = !showResult
            )
        }

        // input field
        TextField(
            value = guess,
            onValueChange = { viewModel.onGuessChanged(it) },
            placeholder = {
                Text(
                    stringResource(R.string.guess_screen_placeholder_whos_that_pokemon),
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
            onClick = { viewModel.onCheckGuess() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(stringResource(R.string.guess_screen_button_submit))
        }

        // attempts left
        Text(
            text = stringResource(R.string.guess_screen_attempts_left, triesLeft),
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
                text = stringResource(R.string.guess_screen_successful_guess, pokemon.name),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        //reset game

        /*
        Button(
            onClick = { viewModel.onResetGame() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset game")
        }
        */
    }
}
