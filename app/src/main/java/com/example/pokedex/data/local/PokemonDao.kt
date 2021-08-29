package com.example.pokedex.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokedex.data.remote.model.pokemon.ResponseItem

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    fun getAllPokemons() : LiveData<List<ResponseItem>>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getPokemon(id: String): LiveData<ResponseItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<ResponseItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: ResponseItem)
}