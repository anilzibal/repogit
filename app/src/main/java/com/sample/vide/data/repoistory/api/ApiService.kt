package com.sample.vide.data.repoistory.api

import com.sample.vide.Constants.Companion.MEDIA
import com.sample.vide.Constants.Companion.TERM
import com.sample.vide.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getVideoList(
        @Query(TERM) searchTerm: String?,
        @Query(MEDIA) entityType: String?
    ): VideoResponse
}