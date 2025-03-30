package com.austral.pocketdex.util

import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.model.PokemonType.*

object MockData {
    val sylveon = Pokemon(
        id = 700,
        name = "Sylveon",
        type = listOf(FAIRY),
        height = 10,
        description = "It wraps its ribbonlike feelers around the arm of its beloved Trainer and walks with him or her."
    )

    val pokemonList = listOf(
        Pokemon(
            id = 1,
            name = "Bulbasaur",
            type = listOf(GRASS, POISON),
            height = 7,
            description = "For some time after its birth, it grows by gaining nourishment from the seed on its back."),

        Pokemon(
            id = 4,
            name = "Charmander",
            type =  listOf(FIRE),
            height = 6,
            description = "From the time it is born, a flame burns at the tip of its tail. Its life would end if the flame were to go out."),

        Pokemon(
            id = 6,
            name = "Charizard",
            type =  listOf(FIRE, FLYING),
            height = 17,
            description = "From the time it is born, a flame burns at the tip of its tail. Its life would end if the flame were to go out."),

        Pokemon(
            id = 7,
            name = "Squirtle",
            type = listOf(WATER),
            height = 5,
            description = "After birth, its back swells and hardens into a shell."
        ),

        sylveon,
    )
}