package com.sample.vide.di.module

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sample.vide.db.room.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
    private var roomApplication = application
    private lateinit var appDatabase: AppDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        appDatabase = Room.databaseBuilder(roomApplication, AppDatabase::class.java, "library_database")
            .fallbackToDestructiveMigration()
            .build()
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesItemDAO(appDatabase: AppDatabase) = appDatabase.getItemDao()
}