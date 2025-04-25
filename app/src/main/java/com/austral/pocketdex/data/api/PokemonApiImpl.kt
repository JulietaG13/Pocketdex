package com.austral.pocketdex.data.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.austral.pocketdex.R
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class PokemonApiImpl @Inject constructor() {

    val TAG: String = "PokemonApiImpl"

    fun getPokemonById(id: Int, context: Context, onSuccess: (PokemonData) -> Unit, onFail: () -> Unit, loadingFinished: () -> Unit) {
        val call: Call<PokemonData> = getService(context).getPokemonById(id)

        Log.d(TAG, "Queueing getPokemonById call")

        call.enqueue(object : Callback<PokemonData> {
            override fun onResponse(response: Response<PokemonData>?, retrofit: Retrofit?) {
                loadingFinished()
                Log.d(TAG, "Request URL: ${response?.raw()?.request()?.url()}")
                if(response?.isSuccess == true) {
                    val pokemon: PokemonData = response.body()
                    Log.d(TAG, "Pokemon: $pokemon")
                    onSuccess(pokemon)
                } else {
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.e(TAG, "Error: $t")
                onFail()
                loadingFinished()
            }
        })
    }

    fun getSpeciesById(id: Int, context: Context, onSuccess: (SpeciesData) -> Unit, onFail: () -> Unit, loadingFinished: () -> Unit) {
        val call: Call<SpeciesData> = getService(context).getSpeciesById(id)

        call.enqueue(object : Callback<SpeciesData> {
            override fun onResponse(response: Response<SpeciesData>?, retrofit: Retrofit?) {
                loadingFinished()
                if(response?.isSuccess == true) {
                    val species: SpeciesData = response.body()
                    Log.d(TAG, "Species: $species")
                    onSuccess(species)
                } else {
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.e(TAG, "Error: $t")
                onFail()
                loadingFinished()
            }
        })
    }

    fun getService(context: Context): PokemonApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.base_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        return retrofit.create(PokemonApi::class.java)
    }
}