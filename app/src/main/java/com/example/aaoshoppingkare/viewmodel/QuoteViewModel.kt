package com.example.aaoshoppingkare.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.repository.QuoteRepository
import com.example.aaoshoppingkare.view.UiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.example.aaoshoppingkare.model.Result
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    val quotes = MutableLiveData<UiState<PagingData<Result>>>()

    val quoteList = repository.getQuotesThroughPagingUsingLiveData().cachedIn(viewModelScope)

    val errorQuote: LiveData<String>
        get() = repository.error


    fun getQuotes() {

        viewModelScope.launch {
            try {
                repository.getQuotesThroughPagingUsingFlow().map { it ->
                    UiState.Success(it) as UiState<PagingData<Result>>
                }
                    .onStart {
                        emit(UiState.Loading)
                    }
                    .onEach { uiState ->
                        quotes.value = uiState
                    }.launchIn(viewModelScope)
            } catch (e: Exception) {
                quotes.value = UiState.Error(e.message!!, e)
            }
        }
    }
}