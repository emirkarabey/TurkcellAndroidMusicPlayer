package com.emirk.turkcellandroidmusicplayer.data.repository

import com.emirk.turkcellandroidmusicplayer.data.remote.dto.toDomain
import com.emirk.turkcellandroidmusicplayer.data.remote.service.ApiService
import com.emirk.turkcellandroidmusicplayer.domain.repository.MusicCategoriesRepository
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import javax.inject.Inject

class MusicCategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MusicCategoriesRepository {
    override suspend fun getMusicCategories(): List<MusicCategory> {
        return apiService.getMusicCategories().musicCategories.map {
            it.toDomain()
        }

    }
}