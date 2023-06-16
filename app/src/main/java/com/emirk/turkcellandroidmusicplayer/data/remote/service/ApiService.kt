package com.emirk.turkcellandroidmusicplayer.data.remote.service

import com.emirk.turkcellandroidmusicplayer.data.remote.dto.MusicCategoriesResponse
import com.emirk.turkcellandroidmusicplayer.util.Constants.CATEGORY_END_POINT
import retrofit2.http.GET

interface ApiService {

    @GET(CATEGORY_END_POINT)
    suspend fun getMusicCategories(
    ): MusicCategoriesResponse
}