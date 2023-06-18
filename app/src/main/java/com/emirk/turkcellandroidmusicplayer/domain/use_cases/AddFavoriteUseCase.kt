package com.emirk.turkcellandroidmusicplayer.domain.use_cases

import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.domain.repository.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(
        music : MusicEntity
    ) {
        repository.addFavorite(music)
    }
}