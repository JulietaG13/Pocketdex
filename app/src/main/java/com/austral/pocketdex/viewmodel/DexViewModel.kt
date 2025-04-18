package com.austral.pocketdex.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.repository.PokemonRepository
import com.austral.pocketdex.util.MockData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DexViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons.asStateFlow()

    private val _foundPokemonIds = MutableStateFlow<Set<Int>>(emptySet())
    val foundPokemonIds: StateFlow<Set<Int>> = _foundPokemonIds.asStateFlow()

    private val _showDialogCard = MutableStateFlow<Boolean>(false)
    val showDialogCard: StateFlow<Boolean> = _showDialogCard.asStateFlow()

    private val _pokemonClicked = MutableStateFlow<Pokemon>(Pokemon.EMPTY)
    val pokemonClicked: StateFlow<Pokemon> = _pokemonClicked.asStateFlow()

    private val _showAll = MutableStateFlow<Boolean>(false)
    val showAll: StateFlow<Boolean> = _showAll.asStateFlow()


    init {
        viewModelScope.launch {
            _pokemons.value = MockData.pokemonList.sortedBy { it.id }
            _foundPokemonIds.value = repository.getFoundPokemonsIds(context).toSet()
        }
    }

    fun onPokemonClicked(pokemon: Pokemon) {
        repository.getPokemonById(
            id = pokemon.id,
            context = context,
            onSuccess = { pokemon ->
                _pokemonClicked.value = pokemon
                _showDialogCard.value = true
            },
            onFail = {},
            loadingFinished = {}
        )
    }

    fun onDismissDialog() {
        _showDialogCard.value = false
    }

    fun onToggleShowAll() {
        _showAll.value = !_showAll.value
    }
}