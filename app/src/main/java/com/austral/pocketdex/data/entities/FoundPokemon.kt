package com.austral.pocketdex.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "found_pokemon")
data class FoundPokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)