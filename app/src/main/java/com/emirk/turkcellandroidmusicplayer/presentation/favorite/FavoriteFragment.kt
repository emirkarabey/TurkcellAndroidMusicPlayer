package com.emirk.turkcellandroidmusicplayer.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.databinding.FragmentFavoriteBinding
import com.emirk.turkcellandroidmusicplayer.presentation.favorite.adapter.FavoriteAdapter
import com.emirk.turkcellandroidmusicplayer.presentation.favorite.adapter.FavoriteItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel.getFavorite()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectEvent()
    }

    private fun initRecyclerViewAdapters() {
        favoritesAdapter = FavoriteAdapter(object : FavoriteItemClickListener {
            override fun onItemClick(musicEntity: MusicEntity) {
                findNavController().navigate(
                    FavoriteFragmentDirections
                        .actionNavigationFavoriteToMusicDetailFragment(
                            title = musicEntity.title,
                            url = musicEntity.url
                        )
                )
            }

            override fun onFavItemClick(musicEntity: MusicEntity, position: Int) {
                viewModel.deleteFavorite(musicEntity.title)
                favoritesAdapter.notifyItemChanged(position)
                viewModel.getFavorite()
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvFav.layoutManager = LinearLayoutManager(context)
        rvFav.adapter = favoritesAdapter
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        //pb show
                    } else {
                        favoritesAdapter.submitList(uiState.musicCategories)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}