package com.emirk.turkcellandroidmusicplayer.domain.repository

import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory


interface MusicCategoriesRepository {
    suspend fun getMusicCategories(): List<MusicCategory>
}