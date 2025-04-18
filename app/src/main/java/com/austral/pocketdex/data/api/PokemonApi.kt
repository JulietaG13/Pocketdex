package com.austral.pocketdex.data.api

import retrofit.Call
import retrofit.http.GET
import retrofit.http.Path

interface PokemonApi {

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Call<PokemonData>

    @GET("pokemon-species/{id}")
    fun getSpeciesById(@Path("id") id: Int): Call<SpeciesData>
}