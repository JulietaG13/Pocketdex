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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.austral.pocketdex.ui.components.DexTopBar
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