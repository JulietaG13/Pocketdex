package com.austral.pocketdex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.ui.components.DexTopBar
import com.austral.pocketdex.ui.components.MovingDiagonalBackground
import com.austral.pocketdex.ui.components.PokeCardDialog
import com.austral.pocketdex.ui.components.PokeListItem
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.util.MockData

@Composable
fun DexScreen() {

    val pokemons = MockData.pokemonList.sortedBy { it.id }
    var showDialogCard by remember { mutableStateOf(false) }
    var pokemonClicked: Pokemon by remember { mutableStateOf(Pokemon.EMPTY) }
    var showAll by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        DexTopBar(
            showAll = showAll,
            onToggle = {showAll = it},
            modifier = Modifier.zIndex(1f)
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            MovingDiagonalBackground(
                modifier = Modifier.zIndex(-1f)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(Dimensions.MinSpriteSize),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimensions.LargePadding)
                    .zIndex(0f),
                horizontalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding),
                verticalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding)
            ) {
                items(pokemons) { pokemon ->
                    PokeListItem(
                        pokemon = pokemon,
                        found = true,
                        onClick = {
                            pokemonClicked = pokemon
                            showDialogCard = true
                        }
                    );
                }
            }
        }
    }

    if (showDialogCard) {
        PokeCardDialog(
            pokemon = pokemonClicked,
            onDismiss = { showDialogCard = false }
        )
    }
}