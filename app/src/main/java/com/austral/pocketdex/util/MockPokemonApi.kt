package com.austral.pocketdex.util

import com.austral.pocketdex.data.api.PokemonApi
import com.austral.pocketdex.data.model.Pokemon

class MockPokemonApi : PokemonApi {
    override fun getPokemonById(id: Int): Pokemon? {
        val poke = MockData.pokemonList.find { it.id == id }

        return poke?: MockData.sylveon
    }
}