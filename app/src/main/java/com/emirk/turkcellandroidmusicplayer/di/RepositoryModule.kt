package com.emirk.turkcellandroidmusicplayer.di

import com.emirk.turkcellandroidmusicplayer.data.remote.repository.MusicCategoriesRepositoryImpl
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
}