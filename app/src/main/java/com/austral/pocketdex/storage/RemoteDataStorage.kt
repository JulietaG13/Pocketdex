package com.austral.pocketdex.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.austral.pocketdex.data.daos.PokemonDao
import com.austral.pocketdex.data.entities.FoundPokemon

@Database(entities = [FoundPokemon::class], version = 1)
abstract class PocketdexDatabase: RoomDatabase() {

    abstract fun PokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PocketdexDatabase? = null

        fun getDatabase(context: Context): PocketdexDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PocketdexDatabase::class.java,
                    "learning_android_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}