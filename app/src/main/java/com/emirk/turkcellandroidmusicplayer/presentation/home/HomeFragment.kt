package com.emirk.turkcellandroidmusicplayer.presentation.home

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
import androidx.recyclerview.widget.RecyclerView
import com.emirk.turkcellandroidmusicplayer.data.remote.dto.MusicDto
import com.emirk.turkcellandroidmusicplayer.databinding.FragmentHomeBinding
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import com.emirk.turkcellandroidmusicplayer.presentation.home.adapter.BaseTitleAdapter
import com.emirk.turkcellandroidmusicplayer.presentation.home.adapter.BaseTitleClickListener
import com.emirk.turkcellandroidmusicplayer.presentation.home.adapter.ItemsAdapter
import com.emirk.turkcellandroidmusicplayer.presentation.home.adapter.ItemsItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var baseTitleAdapter: BaseTitleAdapter
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        if (viewModel.isDataAvailableInFirestore(requireContext())) {
            Log.v("HomeFragment", "Firebasede veri var")
            viewModel.getMusicCategoriesInFirestore()
        } else {
            Log.v("HomeFragment", "Firebasede veri yok")
            viewModel.getMusicCategories(requireContext())
        }

         */
        initRecyclerViewAdapters()
        viewModel.getMusicCategories(requireContext())
        collectEvent()

    }

    private fun initRecyclerViewAdapters() {
        baseTitleAdapter = BaseTitleAdapter(object : BaseTitleClickListener {
            override fun onItemClick(musicCategory: MusicCategory, rvItems: RecyclerView) {
                if (rvItems.visibility == View.GONE) {
                    rvItems.visibility = View.VISIBLE
                    itemsAdapter = ItemsAdapter(object : ItemsItemClickListener {
                        override fun onItemClick(musicDto: MusicDto) {
                            findNavController().navigate(
                                HomeFragmentDirections
                                    .actionNavigationHomeToMusicDetailFragment(
                                        title = musicDto.title,
                                        url = musicDto.url
                                    )
                            )
                        }
                    })
                    rvItems.layoutManager = LinearLayoutManager(requireContext())
                    rvItems.adapter = itemsAdapter
                    itemsAdapter.submitList(musicCategory.items)
                } else {
                    rvItems.visibility = View.GONE
                }

            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvBaseTitle.layoutManager = LinearLayoutManager(context)
        rvBaseTitle.adapter = baseTitleAdapter
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        //pb show
                    } else {
                        uiState.musicCategories?.let {
                            viewModel.saveMusicCategories(
                                it,
                                requireContext()
                            )
                        }
                        baseTitleAdapter.submitList(uiState.musicCategories)
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