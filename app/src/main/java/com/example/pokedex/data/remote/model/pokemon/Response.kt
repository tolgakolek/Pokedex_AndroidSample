package com.example.pokedex.data.remote.model.pokemon

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class ResponseItem(
	@NonNull
	@PrimaryKey
	val id: String,
	val name: String? = null,
	val imageurl: String? = null,
	val ydescription: String? = null,
	val defense: Int? = null,
	val attack: Int? = null
)

