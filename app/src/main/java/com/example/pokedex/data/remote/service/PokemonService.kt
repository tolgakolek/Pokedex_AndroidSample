package com.example.pokedex.data.remote.service

import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/97811a5df2df7304b5bc4fbb9ee018a0339b8a38")
    suspend fun getAllPokemon(): Response<List<ResponseItem>>
}