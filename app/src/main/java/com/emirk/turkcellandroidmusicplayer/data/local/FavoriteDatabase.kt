package com.emirk.turkcellandroidmusicplayer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MusicEntity::class],
    version = 1
)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}