package com.emirk.turkcellandroidmusicplayer.data.repository

import com.emirk.turkcellandroidmusicplayer.data.local.FavoriteDao
import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.domain.repository.FavoriteRepository
import javax.inject.Inject


class FavoriteRepositoryImpl @Inject constructor(
    private val favDao: FavoriteDao
) : FavoriteRepository {
    override suspend fun getFavorites(): List<MusicEntity> {
        return favDao.getAllMusics()
    }

    override suspend fun addFavorite(musicEntity: MusicEntity) {
        favDao.insert(musicEntity = musicEntity)
    }

    override suspend fun deleteFavorite(title: String) {
        favDao.deleteMusic(title = title)
    }
}