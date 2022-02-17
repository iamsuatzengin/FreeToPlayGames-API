package com.suatzengin.freetoplaygamesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.freetoplaygamesapp.databinding.FavoritesItemBinding
import com.suatzengin.freetoplaygamesapp.databinding.RvItemBinding
import com.suatzengin.freetoplaygamesapp.model.Game

class FavoritesAdapter : ListAdapter<Game, FavoritesViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            FavoritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

    }

}


