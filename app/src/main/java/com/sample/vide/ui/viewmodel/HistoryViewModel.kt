package com.sample.vide.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.vide.data.model.VideoDto
import com.sample.vide.data.repoistory.HistoryRepository
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {


    fun getVideoList(): LiveData<List<VideoDto>> {
        return historyRepository.getVideoList()
    }
}
