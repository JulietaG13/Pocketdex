package com.austral.pocketdex.data.api

import com.austral.pocketdex.data.model.Pokemon

interface PokemonApi {
    fun getPokemonById(id: Int): Pokemon?
}