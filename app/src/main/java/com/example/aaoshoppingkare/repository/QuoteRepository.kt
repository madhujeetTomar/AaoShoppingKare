package com.example.aaoshoppingkare.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.model.Result
import com.example.aaoshoppingkare.remote.ApiInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class QuoteRepository @Inject constructor(private val apiInterface: ApiInterface) {

     val error = MutableLiveData<String>()


    suspend fun getQuotes(page: Int): Flow<List<Result>> {
        val currentQuoteList = apiInterface.getQuotes(page)
        var result: List<com.example.aaoshoppingkare.model.Result> = listOf()

        if (currentQuoteList.isSuccessful) {

            result = currentQuoteList.body()!!.results
        }
        else
        {
            error.value = currentQuoteList.errorBody().toString()
        }

        return flow {
            emit(result)
        }
    }
}