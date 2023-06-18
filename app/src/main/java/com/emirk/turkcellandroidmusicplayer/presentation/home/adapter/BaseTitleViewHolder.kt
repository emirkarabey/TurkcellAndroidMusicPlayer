package com.emirk.turkcellandroidmusicplayer.presentation.home.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.emirk.turkcellandroidmusicplayer.databinding.ItemBaseTitleBinding
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory

class BaseTitleViewHolder (
    private val binding: ItemBaseTitleBinding,
    private val baseTitleClickListener: BaseTitleClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(musicCategory: MusicCategory) = binding.apply {

        tvBaseTitle.text = musicCategory.baseTitle
        Log.v("ViewHolder",musicCategory.baseTitle)
        itemView.setOnClickListener {
            baseTitleClickListener.onItemClick(musicCategory = musicCategory, rvItems)
        }
    }
}