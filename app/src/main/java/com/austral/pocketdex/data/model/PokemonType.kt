package com.austral.pocketdex.data.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.austral.pocketdex.R
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


    fun getLocalizedName(context: Context): String {
        return when (this) {
            NORMAL -> context.getString(R.string.pokemon_type_normal)
            FIRE -> context.getString(R.string.pokemon_type_fire)
            WATER -> context.getString(R.string.pokemon_type_water)
            ELECTRIC -> context.getString(R.string.pokemon_type_electric)
            GRASS -> context.getString(R.string.pokemon_type_grass)
            ICE -> context.getString(R.string.pokemon_type_ice)
            FIGHTING -> context.getString(R.string.pokemon_type_fighting)
            POISON -> context.getString(R.string.pokemon_type_poison)
            GROUND -> context.getString(R.string.pokemon_type_ground)
            FLYING -> context.getString(R.string.pokemon_type_flying)
            PSYCHIC -> context.getString(R.string.pokemon_type_psychic)
            BUG -> context.getString(R.string.pokemon_type_bug)
            ROCK -> context.getString(R.string.pokemon_type_rock)
            GHOST -> context.getString(R.string.pokemon_type_ghost)
            DRAGON -> context.getString(R.string.pokemon_type_dragon)
            DARK -> context.getString(R.string.pokemon_type_dark)
            STEEL -> context.getString(R.string.pokemon_type_steel)
            FAIRY -> context.getString(R.string.pokemon_type_fairy)
        }
    }

    companion object {
        fun fromString(type: String): PokemonType? {
            return entries.find { it.name.equals(type, ignoreCase = true) }
        }
    }
}