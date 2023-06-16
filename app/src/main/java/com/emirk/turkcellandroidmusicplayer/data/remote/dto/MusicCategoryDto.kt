package com.emirk.turkcellandroidmusicplayer.data.remote.dto

import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import com.google.gson.annotations.SerializedName

data class MusicCategoryDto(
    @SerializedName("baseTitle")
    val baseTitle: String,
    @SerializedName("items")
    val items: List<MusicDto>
)

fun MusicCategoryDto.toDomain() = MusicCategory(
    baseTitle = baseTitle,
    items = items
)