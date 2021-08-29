package com.example.pokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import com.example.pokedex.databinding.ItemPokemonBinding

class PokedexAdapter (private val listener: PokemonItemListener) : RecyclerView.Adapter<PokemonViewHolder>(){

    interface PokemonItemListener {
        fun onClickedPokemon(pokemonId: String)
    }

    private val items = ArrayList<ResponseItem>()

    fun setItems(items: ArrayList<ResponseItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding: ItemPokemonBinding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(items[position])
}

class PokemonViewHolder(private val itemBinding: ItemPokemonBinding, private val listener: PokedexAdapter.PokemonItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var pokemon: ResponseItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: ResponseItem) {
        this.pokemon = item
        itemBinding.pokemonName.text = item.name
        itemBinding.pokemonDesc.text = item.ydescription
        Glide.with(itemBinding.root)
            .load(item.imageurl)
            .into(itemBinding.imagePokemonUrl)
    }

    override fun onClick(v: View?) {
        listener.onClickedPokemon(pokemon.id)
    }
}

