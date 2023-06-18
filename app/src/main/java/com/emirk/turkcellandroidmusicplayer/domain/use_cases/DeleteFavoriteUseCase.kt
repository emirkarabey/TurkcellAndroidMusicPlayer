package com.emirk.turkcellandroidmusicplayer.domain.use_cases

import com.emirk.turkcellandroidmusicplayer.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(
        title: String
    ) {
        repository.deleteFavorite(title)
    }
}