package com.emirk.turkcellandroidmusicplayer.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.databinding.ItemFavoriteBinding

class FavoriteAdapter (
    private val favoritesItemClickListener: FavoriteItemClickListener
) : ListAdapter<MusicEntity, FavoriteViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MusicEntity>() {
            override fun areItemsTheSame(
                oldItem: MusicEntity,
                newItem: MusicEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MusicEntity,
                newItem: MusicEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding, favoritesItemClickListener)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, position) }
    }
}