package com.emirk.turkcellandroidmusicplayer.domain.use_cases

import com.emirk.turkcellandroidmusicplayer.domain.repository.FirebaseRepository
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import javax.inject.Inject

class SaveMusicCategoriesUseCase  @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) {
    suspend fun invoke(
        musicCategories: List<MusicCategory>
    ) {
        try {
            firebaseRepository.saveMusicCategories(musicCategories)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}