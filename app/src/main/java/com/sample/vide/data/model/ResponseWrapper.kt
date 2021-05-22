package com.sample.vide.data.model

sealed class ResponseWrapper<out T> {
    data class Success<T>(val data: T) : ResponseWrapper<T>()
    data class Error(val errorData: ErrorStateWrapper) : ResponseWrapper<Nothing>()
}
