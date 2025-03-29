package com.austral.pocketdex.util

import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.model.PokemonType.*

object MockData {
    val sylveon = Pokemon(
        id = 700,
        name = "Sylveon",
        type = listOf(FAIRY),
        description = "It wraps its ribbonlike feelers around the arm of its beloved Trainer and walks with him or her."
    )

    val pokemonList = listOf(
        Pokemon(
            id = 1,
            name = "Bulbasaur",
            type = listOf(GRASS, POISON),
            description = "For some time after its birth, it grows by gaining nourishment from the seed on its back."),

        Pokemon(
            id = 4,
            name = "Charmander",
            type =  listOf(FIRE),
            description = "From the time it is born, a flame burns at the tip of its tail. Its life would end if the flame were to go out."),

        Pokemon(
            id = 7,
            name = "Squirtle",
            type = listOf(WATER),
            description = "After birth, its back swells and hardens into a shell."
        ),

        sylveon,
    )
}