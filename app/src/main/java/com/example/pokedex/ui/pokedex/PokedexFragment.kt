package com.example.pokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.databinding.PokedexFragmentBinding
import com.example.pokedex.ui.pokemon.PokedexViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexFragment : Fragment(), PokedexAdapter.PokemonItemListener  {

    lateinit var binding: PokedexFragmentBinding
    private val viewModel: PokedexViewModel by viewModels()
    private lateinit var adapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokedexFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = PokedexAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getPokemonDao().observe(viewLifecycleOwner, Observer {
           if (it.isNotEmpty()) {
               binding.progressBar.visibility = View.GONE
               adapter.setItems(ArrayList(it))
           }
            else {
               binding.progressBar.visibility = View.VISIBLE
               Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
           }
        })
    }
    override fun onClickedPokemon(characterId: String) {
        findNavController().navigate(
            R.id.action_pokedexFragment_to_pokemon_details,
            bundleOf("id" to characterId)
        )
    }

}
