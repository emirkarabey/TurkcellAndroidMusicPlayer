package com.emirk.turkcellandroidmusicplayer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(musicEntity: MusicEntity)

    @Query("DELETE FROM music WHERE title=:title")
    suspend fun deleteMusic(title: String)

    @Query("SELECT * FROM music")
    fun getAllMusics(): List<MusicEntity>
}