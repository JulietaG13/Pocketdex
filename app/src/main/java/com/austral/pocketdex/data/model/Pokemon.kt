package com.austral.pocketdex.data.model

data class Pokemon(
    val id: Int,
    val name: String,
    val type: List<PokemonType>,
    val height: Int,
    val description: String,
) {
    companion object {
        val EMPTY = Pokemon(
            id = 0,
            name = "",
            type = emptyList(),
            height = 0,
            description = ""
        )
    }
}