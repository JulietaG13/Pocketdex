package com.austral.pocketdex.data.model

import androidx.compose.ui.graphics.Color
import com.austral.pocketdex.ui.theme.TypeBug
import com.austral.pocketdex.ui.theme.TypeDark
import com.austral.pocketdex.ui.theme.TypeDragon
import com.austral.pocketdex.ui.theme.TypeElectric
import com.austral.pocketdex.ui.theme.TypeFairy
import com.austral.pocketdex.ui.theme.TypeFighting
import com.austral.pocketdex.ui.theme.TypeFire
import com.austral.pocketdex.ui.theme.TypeFlying
import com.austral.pocketdex.ui.theme.TypeGhost
import com.austral.pocketdex.ui.theme.TypeGrass
import com.austral.pocketdex.ui.theme.TypeGround
import com.austral.pocketdex.ui.theme.TypeIce
import com.austral.pocketdex.ui.theme.TypeNormal
import com.austral.pocketdex.ui.theme.TypePoison
import com.austral.pocketdex.ui.theme.TypePsychic
import com.austral.pocketdex.ui.theme.TypeRock
import com.austral.pocketdex.ui.theme.TypeSteel
import com.austral.pocketdex.ui.theme.TypeWater

enum class PokemonType(val color: Color) {
    NORMAL(TypeNormal),
    FIRE(TypeFire),
    WATER(TypeWater),
    ELECTRIC(TypeElectric),
    GRASS(TypeGrass),
    ICE(TypeIce),
    FIGHTING(TypeFighting),
    POISON(TypePoison),
    GROUND(TypeGround),
    FLYING(TypeFlying),
    PSYCHIC(TypePsychic),
    BUG(TypeBug),
    ROCK(TypeRock),
    GHOST(TypeGhost),
    DRAGON(TypeDragon),
    DARK(TypeDark),
    STEEL(TypeSteel),
    FAIRY(TypeFairy);

    companion object {
        fun fromString(type: String): PokemonType? {
            return entries.find { it.name.equals(type, ignoreCase = true) }
        }
    }
}