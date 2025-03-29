package com.austral.pocketdex.data.model

data class Pokemon(
    val id: Int,
    val name: String,
    val type: List<PokemonType>,
    val description: String
)
