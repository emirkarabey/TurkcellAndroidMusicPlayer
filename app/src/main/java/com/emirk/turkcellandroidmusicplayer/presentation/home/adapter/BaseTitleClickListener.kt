package com.emirk.turkcellandroidmusicplayer.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory

interface BaseTitleClickListener {
    fun onItemClick(musicCategory: MusicCategory, rvItems: RecyclerView)
}