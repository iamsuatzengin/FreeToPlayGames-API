package com.suatzengin.freetoplaygamesapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.freetoplaygamesapp.databinding.FavoritesItemBinding
import com.suatzengin.freetoplaygamesapp.databinding.RvItemBinding
import com.suatzengin.freetoplaygamesapp.model.Game

class FavoritesViewHolder(
    private val binding: FavoritesItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(game: Game) {
        binding.game = game
        binding.executePendingBindings()
    }


}