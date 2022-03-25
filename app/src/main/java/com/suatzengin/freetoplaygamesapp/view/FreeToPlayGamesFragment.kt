package com.suatzengin.freetoplaygamesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.suatzengin.freetoplaygamesapp.R
import com.suatzengin.freetoplaygamesapp.adapter.GamesAdapter
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiFilter
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFreeToPlayGamesBinding
import com.suatzengin.freetoplaygamesapp.viewmodel.FreeToPlayGamesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FreeToPlayGamesFragment : Fragment() {

    private lateinit var binding: FragmentFreeToPlayGamesBinding
    private val viewModel: FreeToPlayGamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_free_to_play_games, container, false
        )

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