package com.sample.vide.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.vide.data.model.VideoDto

@Dao
interface ItemDao {

    @Query("SELECT * FROM VideoDto")
    fun getAll(): LiveData<List<VideoDto>>

    @Insert
    suspend fun insertAll(vararg itemDto: VideoDto)
}