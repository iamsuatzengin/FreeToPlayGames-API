package com.suatzengin.freetoplaygamesapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.freetoplaygamesapp.R
import com.suatzengin.freetoplaygamesapp.adapter.FavoritesAdapter
import com.suatzengin.freetoplaygamesapp.databinding.FragmentFavoritesBinding
import com.suatzengin.freetoplaygamesapp.model.Game
import com.suatzengin.freetoplaygamesapp.viewmodel.FavoritesSharedViewModel


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesSharedViewModel by activityViewModels()
    private lateinit var adapter: FavoritesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorites, container, false
        )

        adapter = FavoritesAdapter {
            val action = FavoritesFragmentDirections.fromFavToDetail(it)
            findNavController().navigate(action)
        }
        binding.rvFavorites.adapter = adapter
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())


        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvFavorites)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameList()

    }

    private fun gameList() {
        viewModel.games.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun alertDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Message!")
            .setMessage("Are you sure to delete this game from Favorites?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                viewModel.games.value?.let { games ->
                    deleteGameFromFavorites(games[position])
                }
            })
            .setNegativeButton("No", null)
        builder.create().show()
    }

    private var simpleCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            alertDialog(position)
        }
    }

    private fun deleteGameFromFavorites(game: Game) {
        viewModel.deleteGameFromFavorites(game)
    }

}