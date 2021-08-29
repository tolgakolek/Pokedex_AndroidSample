package com.example.pokedex.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bumptech.glide.load.engine.Resource
import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDao: PokemonDao
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _pokemon = _id.switchMap { id ->
        pokemonDao.getPokemon(id)
    }

    val pokemon: LiveData<ResponseItem> = _pokemon


    fun start(id: String) {
        _id.value = id
    }

}