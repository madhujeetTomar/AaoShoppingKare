package com.example.aaoshoppingkare.view

import com.example.aaoshoppingkare.model.QuoteList


sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val value: T) : UiState<T>()
    data class Error(val message: String, val cause: java.lang.Exception?= null) : UiState<Nothing>()
}