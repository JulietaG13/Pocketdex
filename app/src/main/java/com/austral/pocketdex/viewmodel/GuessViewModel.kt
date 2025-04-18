package com.austral.pocketdex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.util.MockData
import com.austral.pocketdex.util.MockPokemonApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class GuessViewModel @Inject constructor() : ViewModel() {

    private val _pokemon = MutableStateFlow<Pokemon>(Pokemon.EMPTY)
    val pokemon: StateFlow<Pokemon> = _pokemon.asStateFlow()
    /*all: Random.nextInt(1, 1025 + 1)*/

    private val _guess = MutableStateFlow("")
    val guess: StateFlow<String> = _guess.asStateFlow()

    private val _triesLeft = MutableStateFlow(3)
    val triesLeft: StateFlow<Int> = _triesLeft.asStateFlow()

    private val _revealedHints = MutableStateFlow(emptyList<String>())
    val revealedHints: StateFlow<List<String>> = _revealedHints.asStateFlow()

    private val _showSuccess = MutableStateFlow(false)
    val showSuccess: StateFlow<Boolean> = _showSuccess.asStateFlow()

    private val _showFailure = MutableStateFlow(false)
    val showFailure: StateFlow<Boolean> = _showFailure.asStateFlow()

    val running: StateFlow<Boolean> = combine(showSuccess, showFailure) { success, failure ->
        !success && !failure
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = true
    )


    private val clues = listOf(
        "It's a Water-type!",
        "It evolves at level 16."
    )

    init {
        viewModelScope.launch {
            onResetGame()
        }
    }

    fun onGuessChanged(newGuess: String) {
        _guess.value = newGuess
    }

    fun onCheckGuess() {
        if (_guess.value.isNotBlank() && _triesLeft.value > 0) {
            if (_guess.value.lowercase().trim() == _pokemon.value.name.lowercase()) {
                _showSuccess.value = true
                return
            }
            _triesLeft.value--
            if (_triesLeft.value > 0) {
                _revealedHints.value += clues[_revealedHints.value.size]
            } else {
                _showFailure.value = true
            }
        }
    }

    fun onResetGame() {
        _guess.value = ""
        _triesLeft.value = 3
        _revealedHints.value = emptyList()
        _showSuccess.value = false
        _showFailure.value = false
        viewModelScope.launch {
            _pokemon.value = MockPokemonApi().getPokemonById(MockData.pokemonList.random().id)?:Pokemon.EMPTY
        }
    }
}