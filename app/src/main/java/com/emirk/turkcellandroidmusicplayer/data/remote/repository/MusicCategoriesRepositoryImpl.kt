package com.emirk.turkcellandroidmusicplayer.data.remote.repository

import android.util.Log
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
            Log.v("HomeFragment", "it.items[2].title")
            it.toDomain()
        }
    }
}