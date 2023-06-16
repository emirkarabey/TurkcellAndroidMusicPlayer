package com.emirk.turkcellandroidmusicplayer.presentation.home

import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory

data class HomeUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val musicCategories: List<MusicCategory>? = emptyList(),
)