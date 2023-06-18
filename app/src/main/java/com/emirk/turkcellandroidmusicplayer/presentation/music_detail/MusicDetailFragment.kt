package com.emirk.turkcellandroidmusicplayer.presentation.music_detail

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.emirk.turkcellandroidmusicplayer.R
import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.databinding.FragmentMusicDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicDetailFragment : Fragment() {

    private var _binding: FragmentMusicDetailBinding? = null
    private val binding get() = _binding!!
    val args: MusicDetailFragmentArgs by navArgs()
    private lateinit var mediaPlayer: MediaPlayer
    private val viewModel: MusicDetailViewModel by viewModels()
    private var isFav: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicDetailBinding.inflate(inflater, container, false)
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        mediaPlayer.setAudioAttributes(audioAttributes)
        viewModel.getFavorite()
        collectEvent()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFav) {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_fav)
        } else {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
        mediaPlayer.setDataSource(args.url)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener { player ->
            player.start()
        }
        binding.tvTitle.text = args.title
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress / 100f
                mediaPlayer.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        binding.btnPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        binding.btnPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
            }
        }

        binding.btnFav.setOnClickListener {
            if (it.background.current.equals(R.drawable.ic_baseline_fav)) {
                viewModel.deleteFavorite(args.title)
                it.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                Log.v("MusicDetail", "sadasd")
            } else {
                viewModel.addFavorite(musicEntity = MusicEntity(title = args.title, url = args.url))
                Log.v("MusicDetail", "sadasd22222")
                it.setBackgroundResource(R.drawable.ic_baseline_fav)
            }
        }
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        //pb show
                    } else {
                        uiState.musicCategories!!.forEach {
                            if (it.title == args.title) {
                                isFav = true
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}