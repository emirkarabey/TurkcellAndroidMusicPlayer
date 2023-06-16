package com.emirk.turkcellandroidmusicplayer.domain.ui_model

import com.emirk.turkcellandroidmusicplayer.data.remote.dto.MusicDto

data class MusicCategory(
    val baseTitle: String,
    val items: List<MusicDto>
)