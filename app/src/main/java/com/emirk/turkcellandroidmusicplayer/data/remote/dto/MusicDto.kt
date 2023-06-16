package com.emirk.turkcellandroidmusicplayer.data.remote.dto

import com.emirk.turkcellandroidmusicplayer.domain.ui_model.Music
import com.google.gson.annotations.SerializedName

data class MusicDto(
    @SerializedName("baseCat")
    val baseCat: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

fun MusicDto.toDomain() = Music(
    baseCat = baseCat,
    title = title,
    url = url
)