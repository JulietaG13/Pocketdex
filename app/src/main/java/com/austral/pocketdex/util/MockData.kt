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

        Pokemon(2, "Ivysaur", listOf(GRASS, POISON), 10, "When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs."),
        Pokemon(3, "Venusaur", listOf(GRASS, POISON), 20, "Its plant blooms when it is absorbing solar energy. It stays on the move to seek sunlight."),

        Pokemon(
            id = 4,
            name = "Charmander",
            type =  listOf(FIRE),
            height = 6,
            description = "From the time it is born, a flame burns at the tip of its tail. Its life would end if the flame were to go out."),

        Pokemon(5, "Charmeleon", listOf(FIRE), 11, "It has a barbaric nature. In battle, it whips its fiery tail around and slashes away with sharp claws."),

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

        Pokemon(8, "Wartortle", listOf(WATER), 10, "It is recognized as a symbol of longevity. If its shell has algae on it, that Wartortle is very old."),
        Pokemon(9, "Blastoise", listOf(WATER), 16, "It crushes its foe under its heavy body to cause fainting. In a pinch, it will withdraw inside its shell."),

        Pokemon(10, "Caterpie", listOf(BUG), 3, "For protection, it releases a horrible stench from the antenna on its head to drive away enemies."),
        Pokemon(11, "Metapod", listOf(BUG), 7, "It is waiting for the moment to evolve. At this stage, it can only harden, so it remains motionless to avoid attack."),
        Pokemon(12, "Butterfree", listOf(BUG, FLYING), 11, "It loves the honey of flowers and can locate flower patches that have even tiny amounts of pollen."),
        Pokemon(13, "Weedle", listOf(BUG, POISON), 3, "Its poison stinger is very powerful. Its bright-colored body is intended to warn off its enemies."),
        Pokemon(14, "Kakuna", listOf(BUG, POISON), 6, "Almost incapable of moving, this Pokémon can only harden its shell to protect itself from predators."),
        Pokemon(15, "Beedrill", listOf(BUG, POISON), 10, "It has three poisonous stingers on its forelegs and its tail. They are used to jab its enemy repeatedly."),
        Pokemon(16, "Pidgey", listOf(NORMAL, FLYING), 3, "A common sight in forests and woods. It flaps its wings at ground level to kick up blinding sand."),
        Pokemon(17, "Pidgeotto", listOf(NORMAL, FLYING), 11, "This Pokémon is full of vitality. It constantly flies around its large territory in search of prey."),
        Pokemon(18, "Pidgeot", listOf(NORMAL, FLYING), 15, "This Pokémon flies at Mach 2 speed, seeking prey. Its large talons are feared as wicked weapons."),
        Pokemon(19, "Rattata", listOf(NORMAL), 3, "Will chew on anything with its fangs. If you see one, it’s certain that 40 more live in the area."),
        Pokemon(20, "Raticate", listOf(NORMAL), 7, "Its hind feet are webbed. They act as flippers, so it can swim in rivers and hunt for prey."),

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