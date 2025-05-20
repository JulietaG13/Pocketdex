package com.austral.pocketdex.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.austral.pocketdex.data.entities.FoundPokemon

@Dao
interface PokemonDao {

    @Insert
    suspend fun insert(pokemon: FoundPokemon)

    @Query("SELECT * FROM found_pokemon WHERE id = :id")
    suspend fun getById(id: Long): FoundPokemon?

    @Query("SELECT * FROM found_pokemon WHERE name = :name")
    suspend fun getByName(name: String): FoundPokemon?

    @Query("SELECT * FROM found_pokemon")
    suspend fun getAll(): List<FoundPokemon>

    @Query("SELECT COUNT(*) FROM found_pokemon")
    suspend fun countAll(): Int

    @Query("DELETE FROM found_pokemon WHERE id = :id")
    suspend fun deleteById(id: Long)

}