package com.emirk.turkcellandroidmusicplayer.presentation.music_detail

import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity

data class MusicDetailUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val musicCategories: List<MusicEntity>? = emptyList(),
)