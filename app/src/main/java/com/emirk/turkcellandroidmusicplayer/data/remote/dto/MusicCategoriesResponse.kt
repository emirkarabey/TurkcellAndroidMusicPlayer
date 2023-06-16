package com.emirk.turkcellandroidmusicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MusicCategoriesResponse(
    @SerializedName("musicCategories")
    val musicCategories: List<MusicCategoryDto>
)