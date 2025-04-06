package com.austral.pocketdex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.ui.components.MovingPokeballBackground
import com.austral.pocketdex.ui.components.Profile
import com.austral.pocketdex.ui.components.Sprite
import com.austral.pocketdex.ui.theme.Dimensions

@Composable
fun HomeScreen() {

    MovingPokeballBackground()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .clipToBounds(),
//                .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(150.dp)
        ) {
            Profile(
                image = {
                    Sprite(
                        pokemon = Pokemon.EMPTY,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }

        Spacer(modifier = Modifier.size(Dimensions.MediumSpacer))

        Text(
            text = "Found: ___/1025",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(Dimensions.LargeSpacer * 4))
    }
}