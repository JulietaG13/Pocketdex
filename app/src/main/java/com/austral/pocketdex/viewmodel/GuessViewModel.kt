package com.austral.pocketdex.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.R
import com.austral.pocketdex.data.entities.FoundPokemon
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.repository.PokemonRepository
import com.austral.pocketdex.storage.PocketdexDatabase
import com.austral.pocketdex.util.MockPokemonApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GuessViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: PokemonRepository
) : ViewModel() {

    private val database = PocketdexDatabase.getDatabase(context)

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

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val running: StateFlow<Boolean> = combine(showSuccess, showFailure, isError) { success, failure, isError ->
        !success && !failure && !isError
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = true
    )

    private var clues = listOf(
        "",
        ""
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
                onSuccess()
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

    fun onSuccess() {
        _showSuccess.value = true
        val pokemon = _pokemon.value
        val found = FoundPokemon(id = pokemon.id, name = pokemon.name)
        viewModelScope.launch {
            database.PokemonDao().insert(found)
        }
    }

    fun onResetGame() {
        _guess.value = ""
        _triesLeft.value = 3
        _revealedHints.value = emptyList()
        _showSuccess.value = false
        _showFailure.value = false
        _isError.value = false

        viewModelScope.launch {
            _isLoading.value = true
            getPokemonById(Random.nextInt(1, 700))    // 1025 + 1
        }
    }

    fun getPokemonById(id: Int) {
        _isLoading.value = true
        repository.getPokemonByIdNoDescription(
            id = id,
            context = context,
            onSuccess = { poke ->
                _pokemon.value = poke
                setClues(poke)
            },
            onFail = {
                _pokemon.value = Pokemon.EMPTY
                onError(context.getString(R.string.failure_message_pokemon_not_found))
            },
            loadingFinished = {
                _isLoading.value = false
            }
        )
    }

    fun setClues(pokemon: Pokemon) {
        val typeClue = when (pokemon.type.size) {
            1 -> {
                val (type) = pokemon.type
                context.getString(
                    R.string.guess_screen_clue_pokemon_type_one,
                    type.getLocalizedName(context)
                )
            }
            2 -> {
                val (firstType, secondType) = pokemon.type
                context.getString(
                    R.string.guess_screen_clue_pokemon_type_two,
                    firstType.getLocalizedName(context),
                    secondType.getLocalizedName(context)
                )
            }
            else -> context.getString(R.string.guess_screen_clue_error_1)
        }

        val firstLetterClue = pokemon.name.firstOrNull()?.let {
            context.getString(R.string.guess_screen_clue_first_letter_of_name, it)
        } ?: context.getString(R.string.guess_screen_clue_error_2)

        clues = listOf(typeClue, firstLetterClue)
    }

    fun onError(message: String) {
        _errorMessage.value = message
        _isError.value = true
    }

    fun clearFailureMessage() {
        _errorMessage.value = ""
    }
}