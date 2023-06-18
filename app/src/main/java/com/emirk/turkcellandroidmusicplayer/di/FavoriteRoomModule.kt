package com.emirk.turkcellandroidmusicplayer.di

import android.content.Context
import androidx.room.Room
import com.emirk.turkcellandroidmusicplayer.data.local.FavoriteDao
import com.emirk.turkcellandroidmusicplayer.data.local.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val FAV_DATABASE_NAME = "music"

@[Module InstallIn(SingletonComponent::class)]
object FavoriteRoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return Room.databaseBuilder(context, FavoriteDatabase::class.java, FAV_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db: FavoriteDatabase
    ): FavoriteDao = db.favoriteDao()
}