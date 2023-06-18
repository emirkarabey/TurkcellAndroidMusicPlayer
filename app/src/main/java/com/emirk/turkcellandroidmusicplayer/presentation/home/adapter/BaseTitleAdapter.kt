package com.emirk.turkcellandroidmusicplayer.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.turkcellandroidmusicplayer.databinding.ItemBaseTitleBinding
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory

class BaseTitleAdapter (
    private val baseTitleClickListener: BaseTitleClickListener
) : ListAdapter<MusicCategory, BaseTitleViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MusicCategory>() {
            override fun areItemsTheSame(
                oldItem: MusicCategory,
                newItem: MusicCategory
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MusicCategory,
                newItem: MusicCategory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTitleViewHolder {
        val binding = ItemBaseTitleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseTitleViewHolder(binding, baseTitleClickListener)
    }

    override fun onBindViewHolder(holder: BaseTitleViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}