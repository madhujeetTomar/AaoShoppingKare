package com.example.aaoshoppingkare.viewmodel

import androidx.lifecycle.*
import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.repository.QuoteRepository
import com.example.aaoshoppingkare.view.UiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.example.aaoshoppingkare.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(val repository: QuoteRepository): ViewModel() {


    val quotes = MutableLiveData<UiState<List<Result>>>()

    val errorQuote: LiveData<String>
        get() = repository.error

    fun getQuotes(page: Int) {

        viewModelScope.launch {
            try {
                repository.getQuotes(page).map { it ->
                    UiState.Success(it) as UiState<List<Result>>
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