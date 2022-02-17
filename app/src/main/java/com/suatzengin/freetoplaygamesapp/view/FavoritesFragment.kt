package com.suatzengin.freetoplaygamesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.freetoplaygamesapp.R
import com.suatzengin.freetoplaygamesapp.adapter.FavoritesAdapter
import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.local.GamesDatabase
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFavoritesBinding
import com.suatzengin.freetoplaygamesapp.viewmodel.FavoritesSharedViewModel
import com.suatzengin.freetoplaygamesapp.viewmodel.FavoritesSharedViewModelFactory

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesSharedViewModel
    private lateinit var adapter: FavoritesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorites, container, false
        )

        val dao: GamesDao = GamesDatabase.getDatabase(requireContext()).gamesDao()
        val repository = GamesRepository(dao)
        val factory = FavoritesSharedViewModelFactory(repository)
        viewModel =
            ViewModelProvider(requireActivity(), factory)[FavoritesSharedViewModel::class.java]

        adapter = FavoritesAdapter()
        binding.rvFavorites.adapter = adapter
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.games.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}