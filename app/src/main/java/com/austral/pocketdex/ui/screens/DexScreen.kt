package com.austral.pocketdex.ui.screens

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.austral.pocketdex.ui.components.DexTopBar
import com.austral.pocketdex.ui.components.MovingDiagonalBackground
import com.austral.pocketdex.ui.components.PokeCardDialog
import com.austral.pocketdex.ui.components.PokeListItem
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.viewmodel.DexViewModel

@Composable
fun DexScreen(viewModel: DexViewModel = hiltViewModel<DexViewModel>()) {

    val gridState = rememberLazyGridState()
    val pokemonsState = viewModel.pokemons.collectAsState()
    val pokemons = pokemonsState.value

    val foundPokemonIds by viewModel.foundPokemonIds.collectAsState()
    val showDialogCard by viewModel.showDialogCard.collectAsState()
    val pokemonClicked by viewModel.pokemonClicked.collectAsState()
    val showAll by viewModel.showAll.collectAsState()
    val failureMessage by viewModel.failureMessage.collectAsState()

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    if (showDialogCard) {
        PokeCardDialog(
            pokemon = pokemonClicked,
            onDismiss = { viewModel.onDismissDialog() }
        )
    }

    LaunchedEffect(failureMessage) {
        if (failureMessage.isNotBlank()) {
            Toast.makeText(context, failureMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearFailureMessage()
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisible = visibleItems.lastOrNull()?.index ?: 0
                val totalItems = gridState.layoutInfo.totalItemsCount
                val shouldLoadMore = lastVisible >= totalItems - 6

                if (shouldLoadMore && !viewModel.isLoading.value) {
                    viewModel.loadMorePokemons()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount > 50 -> viewModel.setShowAll(false)
                        dragAmount < -50 -> viewModel.setShowAll(true)
                    }
                }
            }
    ) {

        Crossfade(
            targetState = showAll,
            animationSpec = tween(durationMillis = 300),
            modifier = Modifier.zIndex(1f)
        ) { targetShowAll ->

            DexTopBar(
                showAll = targetShowAll,
                onToggle = {  viewModel.onToggleShowAll() },
                modifier = Modifier.zIndex(1f)
            )
        }


        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            MovingDiagonalBackground(
                modifier = Modifier.zIndex(-1f)
            )

            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Adaptive(Dimensions.MinSpriteSize),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimensions.LargePadding)
                    .zIndex(0f),
                horizontalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding),
                verticalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding),
                contentPadding = PaddingValues(
                    top = Dimensions.LargePadding,
                    bottom = screenHeightDp / 2
                )
            ) {

                items(pokemons) { pokemon ->
                    val found = showAll || pokemon.id in foundPokemonIds
                    PokeListItem(
                        pokemon = pokemon,
                        found = found,
                        onClick = {
                            if (found) {
                                viewModel.onPokemonClicked(pokemon)
                            }
                        }
                    );
                }
            }
        }
    }
}