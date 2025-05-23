package com.austral.pocketdex.data.api

import android.content.Context
import com.austral.pocketdex.R
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.data.model.PokemonType
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun toPokemon(context: Context, pokemonData: PokemonData, speciesData: SpeciesData): Pokemon {
        return Pokemon(
            id = pokemonData.id,
            name = pokemonData.name.title(),
            type = pokemonData.types.map { toPokemonType(it.type) },
            height = pokemonData.height,
            description = toDescription(context, speciesData.flavorTextEntries)
        )
    }

    fun toPokemon(context: Context, pokemonData: PokemonData): Pokemon {
        return Pokemon(
            id = pokemonData.id,
            name = pokemonData.name.title(),
            type = pokemonData.types.map { toPokemonType(it.type) },
            height = pokemonData.height,
            description = ""
        )
    }

    fun toPokemon(context: Context, pokemon: Pokemon, speciesData: SpeciesData): Pokemon {
        return Pokemon(
            id = pokemon.id,
            name = pokemon.name,
            type = pokemon.type,
            height = pokemon.height,
            description = toDescription(context, speciesData.flavorTextEntries)
        )
    }

    fun toPokemonType(type: PokemonTypeData): PokemonType {
        return PokemonType.fromString(type.name) ?: PokemonType.NORMAL
    }

    fun toDescription(context: Context, flavorTextEntries: List<FlavorTextEntryData>): String {
        val flavorText = flavorTextEntries.find {
            it.language.name == context.getString(R.string.api_language)
        }?.flavorText ?: ""

        return flavorText
            .replace("\u000C", "\n")
            .replace("\u00AD\n", "")
            .replace("\u00AD", "")
            .replace(" -\n", " - ")
            .replace("-\n", "-")
            .replace("\n", " ")
            .cleanUppercaseWords()
    }

    fun String.title(): String = split(" ").joinToString(" ") { it.lowercase().replaceFirstChar(Char::uppercaseChar) }

    fun String.cleanUppercaseWords(): String =
        split(" ").joinToString(" ") { word ->
            if (word.any { it.isUpperCase() }) {
                word.lowercase().replaceFirstChar(Char::uppercaseChar)
            } else {
                word
            }
        }
}