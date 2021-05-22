package com.sample.vide.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.vide.data.model.VideoDto

@Database(entities = arrayOf(VideoDto::class),version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun getItemDao():ItemDao
}