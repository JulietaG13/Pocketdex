package com.austral.pocketdex.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.R
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.repository.PokemonRepository
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

    private val _failureMessage = MutableStateFlow<String>("")
    val failureMessage: StateFlow<String> = _failureMessage.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var loadedUntilId = 0
    private val batchSize = 16
    private val maxPokemons = 1025


    init {
        viewModelScope.launch {
            loadMorePokemons()
            loadMorePokemons()

            _foundPokemonIds.value = repository.getFoundPokemonsIds(context).toSet()
        }
    }

    fun onPokemonClicked(pokemon: Pokemon) {
        _isLoading.value = true
        repository.getPokemonById(
            id = pokemon.id,
            context = context,
            onSuccess = { poke ->
                _pokemonClicked.value = poke
                _showDialogCard.value = true
            },
            onFail = {
                onFail(context.getString(R.string.failure_message_pokemon_not_found))
            },
            loadingFinished = {
                _isLoading.value = false
            }
        )
    }

    fun loadMorePokemons() {
        if (_isLoading.value) return

        _isLoading.value = true

        val from = loadedUntilId + 1
        var to = loadedUntilId + batchSize

        if (to > maxPokemons) {
            if (to - batchSize < maxPokemons) {
                to = maxPokemons
            } else {
                _isLoading.value = false
                onFail(context.getString(R.string.failure_message_no_more_pokemons_found))
                return
            }
        }

        repository.getPokemonList(
            from = from,
            to = to,
            context = context,
            onSuccess = { list ->
                _pokemons.value = (_pokemons.value + list).distinctBy { it.id }.sortedBy { it.id }
                loadedUntilId = to
            },
            onFail = {
                onFail(context.getString(R.string.failure_message_could_not_load_pokemon_list))
            },
            loadingFinished = {
                _isLoading.value = false
            }
        )
    }

    fun onDismissDialog() {
        _showDialogCard.value = false
    }

    fun onToggleShowAll() {
        _showAll.value = !_showAll.value
    }

    fun onFail(message: String) {
        _failureMessage.value = message
    }

    fun clearFailureMessage() {
        _failureMessage.value = ""
    }
}