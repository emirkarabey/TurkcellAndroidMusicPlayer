package com.emirk.turkcellandroidmusicplayer.di

import com.emirk.turkcellandroidmusicplayer.data.repository.FavoriteRepositoryImpl
import com.emirk.turkcellandroidmusicplayer.data.repository.FirebaseRepositoryImpl
import com.emirk.turkcellandroidmusicplayer.data.repository.MusicCategoriesRepositoryImpl
import com.emirk.turkcellandroidmusicplayer.domain.repository.FavoriteRepository
import com.emirk.turkcellandroidmusicplayer.domain.repository.FirebaseRepository
import com.emirk.turkcellandroidmusicplayer.domain.repository.MusicCategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun musicCategoriesRepository(MusicCategoriesRepositoryImpl: MusicCategoriesRepositoryImpl): MusicCategoriesRepository
    @Binds
    @Singleton
    abstract fun firebaseRepository(FirebaseRepositoryImpl: FirebaseRepositoryImpl): FirebaseRepository
    @Binds
    @Singleton
    abstract fun favoriteRepository(FavoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}