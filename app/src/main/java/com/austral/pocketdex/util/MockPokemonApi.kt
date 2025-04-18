package com.austral.pocketdex.util

import com.austral.pocketdex.data.model.Pokemon

class MockPokemonApi {
    fun getPokemonById(id: Int): Pokemon? {
        val poke = MockData.pokemonList.find { it.id == id }

        return poke?: MockData.sylveon
    }
}