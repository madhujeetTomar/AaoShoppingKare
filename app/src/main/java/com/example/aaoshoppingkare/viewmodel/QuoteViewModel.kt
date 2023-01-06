package com.example.aaoshoppingkare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    val quotes : LiveData<QuoteList>
    get() = repository.quoteData

    init {
      viewModelScope.launch {

          repository.getQuotes(1)
      }
    }
}