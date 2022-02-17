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
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFreeToPlayGamesBinding
import com.suatzengin.freetoplaygamesapp.viewmodel.FreeToPlayGamesViewModel


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
        viewModel = ViewModelProvider(this)[FreeToPlayGamesViewModel::class.java]
        val adapter = GamesAdapter {
            val action = FreeToPlayGamesFragmentDirections.toDetail(it)
            findNavController().navigate(action)
        }
        binding.rvGames.adapter = adapter
        viewModel.games.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        return binding.root
    }

}