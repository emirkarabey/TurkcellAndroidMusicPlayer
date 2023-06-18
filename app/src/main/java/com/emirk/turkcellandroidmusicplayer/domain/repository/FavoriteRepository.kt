package com.emirk.turkcellandroidmusicplayer.domain.repository

import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity

interface FavoriteRepository {
    suspend fun getFavorites(): List<MusicEntity>
    suspend fun addFavorite(musicEntity: MusicEntity)
    suspend fun deleteFavorite(title: String)
}