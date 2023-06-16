package com.emirk.turkcellandroidmusicplayer.domain.use_cases

import android.util.Log
import com.emirk.turkcellandroidmusicplayer.common.Resource
import com.emirk.turkcellandroidmusicplayer.domain.repository.MusicCategoriesRepository
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMusicCategoriesUseCase @Inject constructor(
    private val repository: MusicCategoriesRepository
) {
    operator fun invoke(
    ): Flow<Resource<List<MusicCategory>>> = flow {
        try {
            Log.v("HomeFragment", "use case try")
            emit(Resource.Loading())
            val categories = repository.getMusicCategories()
            Log.v("HomeFragment", categories[0].baseTitle)
            emit(Resource.Success(data = categories))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}