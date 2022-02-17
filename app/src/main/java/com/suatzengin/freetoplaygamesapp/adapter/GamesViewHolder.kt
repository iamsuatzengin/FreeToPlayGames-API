package com.suatzengin.freetoplaygamesapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.freetoplaygamesapp.databinding.RvItemBinding
import com.suatzengin.freetoplaygamesapp.model.Game

class GamesViewHolder(private val binding: RvItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(game: Game, onItemClick: (Game) -> Unit) {
        binding.game = game
        binding.executePendingBindings()

        binding.card.setOnClickListener {
            onItemClick(game)
        }
    }
}