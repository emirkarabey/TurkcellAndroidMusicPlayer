package com.emirk.turkcellandroidmusicplayer.domain.repository

import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory

interface FirebaseRepository {
    suspend fun getMusicCategories(): List<MusicCategory>?
    suspend fun saveMusicCategories(musicCategories: List<MusicCategory>?)
}