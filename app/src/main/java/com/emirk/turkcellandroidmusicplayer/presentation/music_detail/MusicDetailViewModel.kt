package com.emirk.turkcellandroidmusicplayer.presentation.music_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.turkcellandroidmusicplayer.common.Resource
import com.emirk.turkcellandroidmusicplayer.data.local.MusicEntity
import com.emirk.turkcellandroidmusicplayer.domain.use_cases.AddFavoriteUseCase
import com.emirk.turkcellandroidmusicplayer.domain.use_cases.DeleteFavoriteUseCase
import com.emirk.turkcellandroidmusicplayer.domain.use_cases.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicDetailViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MusicDetailUiState())
    val uiState: StateFlow<MusicDetailUiState> = _uiState.asStateFlow()
    val favoriteLiveData: MutableLiveData<List<MusicEntity>> = MutableLiveData()
    fun addFavorite(musicEntity: MusicEntity) = viewModelScope.launch {
        addFavoriteUseCase.invoke(music = musicEntity)
    }

    fun getFavorite() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(userMessage = result.message)
                    }
                }
                is Resource.Loading -> {
                    _uiState.update { state ->
                        state.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(musicCategories = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun deleteFavorite(title: String) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(title = title)
    }
}