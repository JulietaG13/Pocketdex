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

        Pokemon(
            id = 25,
            name = "Pikachu",
            type = listOf(ELECTRIC),
            height = 4,
            description = "It has small electric sacs on both its cheeks. If threatened, it discharges electricity."
        ),

        Pokemon(
            id = 133,
            name = "Eevee",
            type = listOf(NORMAL),
            height = 3,
            description = "Thanks to its unstable genetic makeup, this special Pokémon can evolve into many different forms."
        ),

        Pokemon(
            id = 150,
            name = "Mewtwo",
            type = listOf(PSYCHIC),
            height = 20,
            description = "A Pokémon created by recombining Mew's genes. It’s said to be the most powerful psychic Pokémon."
        ),

        Pokemon(
            id = 181,
            name = "Ampharos",
            type = listOf(ELECTRIC),
            height = 14,
            description = "The tip of its tail shines brightly. It has been treasured since ancient times as a beacon."
        ),

        Pokemon(
            id = 245,
            name = "Suicune",
            type = listOf(WATER),
            height = 20,
            description = "Suicune embodies the compassion of a pure spring of water. It runs across the land with grace."
        ),

        Pokemon(
            id = 252,
            name = "Treecko",
            type = listOf(GRASS),
            height = 5,
            description = "Treecko is the Grass-type Pokémon of Hoenn. It has strong claws and it can climb trees with ease."
        ),

        Pokemon(
            id = 374,
            name = "Beldum",
            type = listOf(STEEL, PSYCHIC),
            height = 6,
            description = "Beldum’s body is composed of a single metallic component. It uses its psychic powers to move."
        ),

        Pokemon(
            id = 405,
            name = "Luxray",
            type = listOf(ELECTRIC),
            height = 15,
            description = "Luxray can see in the dark using its highly developed eyes. It can detect the presence of enemies."
        ),

        Pokemon(
            id = 778,
            name = "Mimikyu",
            type = listOf(GHOST, FAIRY),
            height = 2,
            description = "Mimikyu wears a disguise to resemble Pikachu. It does this to make friends but hides its true appearance."
        ),

        Pokemon(
            id = 570,
            name = "Zorua",
            type = listOf(GHOST, DARK),
            height = 6,
            description = "Zorua uses illusions to deceive others. It takes the form of anyone it wants to impersonate."
        ),

        Pokemon(
            id = 28,
            name = "Sandslash",
            type = listOf(GROUND),
            height = 10,
            description = "It curls up into a ball to protect itself from attacks. It can dig tunnels at high speeds."
        ),
        Pokemon(
            id = 777,
            name = "Togedemaru",
            type = listOf(ELECTRIC, STEEL),
            height = 4,
            description = "Togedemaru stores electricity in its fur, and discharges it when threatened. Its prickly body is made to repel attacks."
        ),

        Pokemon(
            id = 16,
            name = "Pidgey",
            type = listOf(NORMAL, FLYING),
            height = 3,
            description = "Pidgey is a small, brown bird Pokémon. It uses its keen vision to spot prey from a great distance."
        ),

        Pokemon(
            id = 39,
            name = "Jigglypuff",
            type = listOf(FAIRY, NORMAL),
            height = 5,
            description = "Jigglypuff sings to calm down its foes, lulling them to sleep with its soft voice."
        ),

        Pokemon(
            id = 84,
            name = "Doduo",
            type = listOf(NORMAL, FLYING),
            height = 14,
            description = "Doduo has two heads. They act independently and are always looking around for danger."
        ),

        Pokemon(
            id = 132,
            name = "Ditto",
            type = listOf(NORMAL),
            height = 3,
            description = "Ditto has the ability to copy the physical appearance of any other Pokémon it sees."
        ),

        Pokemon(
            id = 243,
            name = "Raikou",
            type = listOf(ELECTRIC),
            height = 19,
            description = "Raikou embodies the thunderstorm. It is a fierce and powerful Electric-type Legendary Pokémon."
        ),

        Pokemon(
            id = 244,
            name = "Entei",
            type = listOf(FIRE),
            height = 21,
            description = "Entei embodies the passion of magma. It is said that whenever it roars, a volcano erupts somewhere in the world."
        ),
    )
}