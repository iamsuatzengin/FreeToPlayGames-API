package com.suatzengin.freetoplaygamesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.suatzengin.freetoplaygamesapp.R
import com.suatzengin.freetoplaygamesapp.adapter.GamesAdapter
import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.local.GamesDatabase
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiFilter
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFreeToPlayGamesBinding
import com.suatzengin.freetoplaygamesapp.viewmodel.FreeToPlayGamesViewModel
import com.suatzengin.freetoplaygamesapp.viewmodel.FreeToPlayGamesViewModelFactory


class FreeToPlayGamesFragment : Fragment() {

    private lateinit var binding: FragmentFreeToPlayGamesBinding
    private lateinit var viewModel: FreeToPlayGamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_free_to_play_games, container, false
        )

        val dao: GamesDao = GamesDatabase.getDatabase(requireContext()).gamesDao()
        val repository = GamesRepository(dao)
        val viewModelFactory = FreeToPlayGamesViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[FreeToPlayGamesViewModel::class.java]
        val adapter = GamesAdapter {
            val action = FreeToPlayGamesFragmentDirections.toDetail(it)
            findNavController().navigate(action)
        }

        binding.rvGames.adapter = adapter
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.games.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)

        })
        binding.chipGroup.isSelectionRequired = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            chipAll.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    viewModel!!.updateFilter(GamesApiFilter.SHOW_ALL)
                }
            }
            chipBrowser.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    viewModel!!.updateFilter(GamesApiFilter.SHOW_BROWSER)
                }
            }
            chipPc.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    viewModel!!.updateFilter(GamesApiFilter.SHOW_PC)
                }
            }
        }
    }

}