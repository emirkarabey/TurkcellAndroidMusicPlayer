package com.emirk.turkcellandroidmusicplayer.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.turkcellandroidmusicplayer.common.Resource
import com.emirk.turkcellandroidmusicplayer.data.shared_preferences.SharedPreferencesManager
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import com.emirk.turkcellandroidmusicplayer.domain.use_cases.GetFirestoreDataUseCase
import com.emirk.turkcellandroidmusicplayer.domain.use_cases.GetMusicCategoriesUseCase
import com.emirk.turkcellandroidmusicplayer.domain.use_cases.SaveMusicCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMusicCategoriesUseCase: GetMusicCategoriesUseCase,
    private val saveMusicCategoriesUseCase: SaveMusicCategoriesUseCase,
    private val getFirestoreDataUseCase: GetFirestoreDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun saveMusicCategories(musicCategories: List<MusicCategory>, context: Context) =
        viewModelScope.launch {
            saveMusicCategoriesUseCase.invoke(musicCategories)
            //getMusicCategoriesInFirestore()
            val sharedPreferencesManager = SharedPreferencesManager(context)
            sharedPreferencesManager.saveData("firestoreIsEmpty", true)
        }

    fun getMusicCategoriesInFirestore() = viewModelScope.launch {
        getFirestoreDataUseCase.invoke().collect { result ->
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

    fun isDataAvailableInFirestore(context: Context): Boolean {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        return sharedPreferencesManager.getBoolean("firestoreIsEmpty", false)
    }

    /*
    fun getMusicCategories(context: Context) = viewModelScope.launch {
        getMusicCategoriesUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    result.data?.let {
                        saveMusicCategories(
                            musicCategories = it,
                            context = context
                        )
                    }
                }
            }
        }
    }

     */
    fun getMusicCategories(context: Context) = viewModelScope.launch {
        getMusicCategoriesUseCase.invoke().collect { result ->
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

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }
}

