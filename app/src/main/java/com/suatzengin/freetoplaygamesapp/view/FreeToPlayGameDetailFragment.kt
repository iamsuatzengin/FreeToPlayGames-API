package com.suatzengin.freetoplaygamesapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.suatzengin.freetoplaygamesapp.R
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFreeToPlayGameDetailBinding
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFreeToPlayGamesBinding


class FreeToPlayGameDetailFragment : Fragment() {

    private lateinit var binding: FragmentFreeToPlayGameDetailBinding
    private val args: FreeToPlayGameDetailFragmentArgs by navArgs()

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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.favorite -> Toast.makeText(
                requireContext(),
                "Favorilere eklendi",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}