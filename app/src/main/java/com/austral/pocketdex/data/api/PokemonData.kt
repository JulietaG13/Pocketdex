package com.austral.pocketdex.data.api

import com.google.gson.annotations.SerializedName

data class PokemonData(
    val id: Int,
    val name: String,
    val types: List<PokemonObjectTypeData>,
    val height: Int
)

data class PokemonObjectTypeData(
    val type: PokemonTypeData
)

data class PokemonTypeData(
    val name: String
)

/*-------------------------------------------------------------*/

// $.flavor_text_entries[#].flavor_text  at language.name="en" and version="y"?
data class SpeciesData(
    val id: Int,
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryData>
)

data class FlavorTextEntryData(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: LanguageData,
    val version: VersionData
)

data class LanguageData(
    val name: String
)

data class VersionData(
    val version: String
)