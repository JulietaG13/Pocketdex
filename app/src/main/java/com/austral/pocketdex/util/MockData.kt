package com.austral.pocketdex.util

import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.model.PokemonType.*

object MockData {
    val pokemonList = listOf(
        Pokemon(1, "Bulbasaur", listOf(GRASS, POISON), "For some time after its birth, it grows by gaining nourishment from the seed on its back."),
        Pokemon(4, "Charmander", listOf(FIRE), "From the time it is born, a flame burns at the tip of its tail. Its life would end if the flame were to go out."),
        Pokemon(700, "Sylveon", listOf(FAIRY), "It wraps its ribbonlike feelers around the arm of its beloved Trainer and walks with him or her."),
    )
}