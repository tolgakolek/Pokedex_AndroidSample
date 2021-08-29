package com.example.pokedex.data.local

import android.content.Context
import androidx.room.*
import com.example.pokedex.data.remote.model.pokemon.ResponseItem

@Database(entities = [ResponseItem::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao():PokemonDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "pokemons")
                .fallbackToDestructiveMigration()
                .build()
    }
}