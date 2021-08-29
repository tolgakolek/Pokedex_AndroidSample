package com.example.pokedex.data.remote.source.pokemon

import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import com.example.pokedex.data.remote.service.PokemonService
import com.example.pokedex.data.util.DataState
import com.example.pokedex.data.util.apiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(private val pokemonService: PokemonService) :
    PokemonRemoteDataSource {
    override suspend fun getAllPokemon(): Flow<DataState<List<ResponseItem>>> {
        return apiCall { pokemonService.getAllPokemon() }
    }
}