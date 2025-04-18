package com.austral.pocketdex.data.repository

import android.content.Context
import com.austral.pocketdex.data.api.PokemonApiImpl
import com.austral.pocketdex.data.api.PokemonMapper
import com.austral.pocketdex.data.model.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApiImpl,
    private val pokemonMapper: PokemonMapper
) {

    fun getPokemonById(
        id: Int,
        context: Context,
        onSuccess: (Pokemon) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        pokemonApi.getPokemonById(
            id = id,
            context = context,
            onSuccess = { pokemonData ->
                pokemonApi.getSpeciesById(
                    id = id,
                    context = context,
                    onSuccess = { speciesData ->
                        onSuccess(pokemonMapper.toPokemon(context, pokemonData, speciesData))
                    },
                    onFail = onFail,
                    loadingFinished = loadingFinished
                )
            },
            onFail = onFail,
            loadingFinished = loadingFinished
        )
    }
}