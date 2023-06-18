package com.emirk.turkcellandroidmusicplayer.presentation.favorite.adapter

import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity

interface FavoriteItemClickListener {
    fun onItemClick(musicEntity: MusicEntity)
    fun onFavItemClick(musicEntity: MusicEntity, position: Int)
}