package com.example.pokedex.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pokedex.data.remote.model.pokemon.ResponseItem
import com.example.pokedex.databinding.PokemonDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {

    lateinit var binding: PokemonDetailsFragmentBinding
    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bindCharacter(it!!)
                binding.progressBar.visibility = View.GONE
                binding.pokemonCl.visibility = View.VISIBLE
            }
            else {
                Toast.makeText(activity, "Loading", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.VISIBLE
                binding.pokemonCl.visibility = View.GONE
            }

        })
    }

    private fun bindCharacter(pokemon: ResponseItem) {
        binding.name.text = pokemon.name
        binding.attack.text = "Attack : " + pokemon.attack.toString()
        binding.defence.text = "Defence : " + pokemon.defense.toString()
        binding.description.text = pokemon.ydescription
        Glide.with(binding.root)
            .load(pokemon.imageurl)
            .into(binding.image)
    }
}