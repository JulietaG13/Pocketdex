package com.austral.pocketdex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.ui.components.MovingPokeballBackground
import com.austral.pocketdex.ui.components.Profile
import com.austral.pocketdex.ui.components.Sprite
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val found by viewModel.found.collectAsState()
    val total = viewModel.total

    MovingPokeballBackground()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .clipToBounds(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Dimensions.RoundedCorner))
                .background(Color.White.copy(alpha = 0.5f))
                .padding(Dimensions.LargePadding * 3)
                .padding(bottom = Dimensions.LargePadding * 3)
        ) {
            Column(
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
                    text = "Found: $found/$total",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.size(Dimensions.LargeSpacer * 4))
    }
}