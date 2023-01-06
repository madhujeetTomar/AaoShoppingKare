package com.example.aaoshoppingkare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aaoshoppingkare.repository.QuoteRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: QuoteRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewModel(repository) as T
    }
}