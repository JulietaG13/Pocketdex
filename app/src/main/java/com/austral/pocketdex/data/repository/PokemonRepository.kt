package com.austral.pocketdex.data.repository

import android.content.Context
import com.austral.pocketdex.data.api.PokemonApiImpl
import com.austral.pocketdex.data.api.PokemonMapper
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.util.MockData
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

    fun getFoundPokemonsIds(context: Context): List<Int> {
        return listOf(1, 2, 3, 10, 11, 13, 15, 16, 17, 28, 39, 84, 113, 700)
    }
}