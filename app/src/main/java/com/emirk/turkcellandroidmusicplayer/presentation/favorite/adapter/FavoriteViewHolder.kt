package com.emirk.turkcellandroidmusicplayer.presentation.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.databinding.ItemFavoriteBinding

class FavoriteViewHolder(
    private val binding: ItemFavoriteBinding,
    private val favoritesItemClickListener: FavoriteItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(musicEntity: MusicEntity, position: Int) = binding.apply {

        tvTitle.text = musicEntity.title

        btnPlay.setOnClickListener {
            favoritesItemClickListener.onItemClick(musicEntity)
        }

        btnFav.setOnClickListener {
            favoritesItemClickListener.onFavItemClick(musicEntity, position)
        }
    }
}