package com.emirk.turkcellandroidmusicplayer.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.emirk.turkcellandroidmusicplayer.data.remote.dto.MusicDto
import com.emirk.turkcellandroidmusicplayer.databinding.ItemItemsBinding

class ItemsViewHolder  (
    private val binding: ItemItemsBinding,
    private val itemsItemClickListener: ItemsItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(musicDto: MusicDto) = binding.apply {
        tvItems.text = musicDto.title

        itemView.setOnClickListener {
            itemsItemClickListener.onItemClick(musicDto = musicDto)
        }
    }
}