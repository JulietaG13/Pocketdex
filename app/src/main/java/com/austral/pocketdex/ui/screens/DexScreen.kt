package com.austral.pocketdex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.austral.pocketdex.ui.components.MovingDiagonalBackground
import com.austral.pocketdex.ui.components.PokeCardDialog
import com.austral.pocketdex.ui.components.PokeListItem
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.util.MockPokemonApi

@Composable
fun DexScreen() {

    val pokemons = (1..100).toList()    // last = 1025
    var showDialogCard by remember { mutableStateOf(false) }
    var idClicked by remember { mutableIntStateOf(0) }

    MovingDiagonalBackground()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimensions.MinSpriteSize),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimensions.LargePadding * 2)
            .padding(horizontal = Dimensions.LargePadding),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding),
        verticalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding)
    ) {
        items(pokemons) { id ->
            PokeListItem(
                id = id,
                found = true,
                onClick = {
                    idClicked = id
                    showDialogCard = true
                }
                );
        }
    }

    if (showDialogCard) {
        val poke = MockPokemonApi().getPokemonById(idClicked)

        PokeCardDialog(
            pokemon = poke!!,
            onDismiss = { showDialogCard = false }
        )
    }
}