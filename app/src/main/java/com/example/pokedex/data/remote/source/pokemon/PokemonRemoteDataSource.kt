package com.example.pokedex.data.remote.source.pokemon

import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import com.example.pokedex.data.util.DataState
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getAllPokemon(): Flow<DataState<List<ResponseItem>>>
}