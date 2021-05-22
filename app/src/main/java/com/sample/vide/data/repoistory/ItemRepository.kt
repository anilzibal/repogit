package com.sample.vide.data.repoistory

import com.sample.vide.data.model.VideoDto
import com.sample.vide.data.model.VideoResponse
import com.sample.vide.data.repoistory.api.ApiService
import com.sample.vide.db.room.ItemDao
import com.sample.vide.di.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemRepository() {
    init {
        MyApp.appComponent.inject(this)
    }

    @Inject
    lateinit var itemDao: ItemDao

    @Inject
    lateinit var apiService: ApiService


    suspend fun getVideoList(searchTerm: String, entityType: String): VideoResponse {
        return apiService.getVideoList(searchTerm, entityType)
    }

    fun insertVideo(video: VideoDto) {
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.insertAll(video)
        }
    }

}
