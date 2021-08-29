package com.example.pokedex.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import com.example.pokedex.data.remote.source.pokemon.PokemonRemoteDataSource
import com.example.pokedex.data.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    private fun getAllPokemon(){
        viewModelScope.launch {
            pokemonRemoteDataSource.getAllPokemon().collect {
                when(it){
                    is DataState.Success -> {
                        pokemonDao.insertAll(it.data)
                    }
                    is Error -> {
                        val error = it.message
                    }
                }
            }
        }
    }

    fun getPokemonDao(): LiveData<List<ResponseItem>> {
        getAllPokemon()
        return pokemonDao.getAllPokemons()
    }
}





