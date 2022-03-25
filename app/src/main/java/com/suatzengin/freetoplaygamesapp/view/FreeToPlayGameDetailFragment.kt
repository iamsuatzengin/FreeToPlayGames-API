package com.suatzengin.freetoplaygamesapp.view


import android.os.Bundle

import android.view.*
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.suatzengin.freetoplaygamesapp.R
import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.local.GamesDatabase
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFreeToPlayGameDetailBinding
import com.suatzengin.freetoplaygamesapp.viewmodel.FavoritesSharedViewModel
import com.suatzengin.freetoplaygamesapp.viewmodel.FavoritesSharedViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FreeToPlayGameDetailFragment : Fragment() {

    private lateinit var binding: FragmentFreeToPlayGameDetailBinding
    private val args: FreeToPlayGameDetailFragmentArgs by navArgs()

    private val viewModel: FavoritesSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_free_to_play_game_detail,
            container,
            false
        )
        binding.game = args.game

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun addFavorites(){
        viewModel.addGameFavorites(args.game)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                addFavorites()
                Toast.makeText(
                    requireContext(),
                    "Added Favorites",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        val icon = menu.findItem(R.id.favorite).icon

        viewModel.games.observe(viewLifecycleOwner, Observer{
            if(args.game in it){
                icon.setTint(resources.getColor(R.color.icon))
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}