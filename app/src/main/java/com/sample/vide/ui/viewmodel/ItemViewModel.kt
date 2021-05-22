package com.sample.vide.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.vide.data.model.VideoDto
import com.sample.vide.data.repoistory.ItemRepository
import com.sample.vide.utills.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _progressVisible: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent<Boolean>() }
    val progressVisible: LiveData<Boolean>
        get() = _progressVisible

    private val _snackBarMessage: SingleLiveEvent<String> by lazy { SingleLiveEvent<String>() }
    val snackBarMsg: LiveData<String>
        get() = _snackBarMessage

    private val _itemLiveData: SingleLiveEvent<ArrayList<VideoDto>> by lazy { SingleLiveEvent<ArrayList<VideoDto>>() }
    val itemLiveData: LiveData<ArrayList<VideoDto>>
        get() = _itemLiveData

    init {
        getVideoList()
    }


    fun addVideo(video: VideoDto) {
        return itemRepository.insertVideo(video)
    }


    private fun getVideoList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressVisible.postValue(true)
            try {
                _progressVisible.postValue(false)

                val response = itemRepository.getVideoList(
                    "Michael+jackson",
                    "musicVideo"
                )
                _itemLiveData.postValue(response.results as ArrayList<VideoDto>?)

            } catch (exception: Exception) {
                _progressVisible.postValue(false)

                _snackBarMessage.postValue(exception.toString())
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}
