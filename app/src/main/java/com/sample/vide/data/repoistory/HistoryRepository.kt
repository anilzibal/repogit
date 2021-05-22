package com.sample.vide.data.repoistory

import androidx.lifecycle.LiveData
import com.sample.vide.data.model.VideoDto
import com.sample.vide.db.room.ItemDao
import com.sample.vide.di.MyApp
import javax.inject.Inject

class HistoryRepository() {

    init {
        MyApp.appComponent.inject(this)
    }

    @Inject
    lateinit var itemDao: ItemDao

    fun getVideoList(): LiveData<List<VideoDto>> {
        return itemDao.getAll()
    }
}
