package com.emirk.turkcellandroidmusicplayer.presentation.home.adapter

import com.emirk.turkcellandroidmusicplayer.data.remote.dto.MusicDto

interface ItemsItemClickListener {
    fun onItemClick(musicDto: MusicDto)
}