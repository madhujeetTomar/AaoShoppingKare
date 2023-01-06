package com.example.aaoshoppingkare.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.model.Result
import com.example.aaoshoppingkare.paging.QuotePagingSource
import com.example.aaoshoppingkare.remote.ApiInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class QuoteRepository @Inject constructor(private val apiInterface: ApiInterface) {

     val error = MutableLiveData<String>()

     fun getQuotesThroughPagingUsingFlow() = Pager(
         config = PagingConfig(20,40),
         pagingSourceFactory = {QuotePagingSource(apiInterface) }
     ).flow

    fun getQuotesThroughPagingUsingLiveData() = Pager(
        config = PagingConfig(20,40),
        pagingSourceFactory = {QuotePagingSource(apiInterface) }
    ).liveData

    suspend fun getQuotes(page: Int): Flow<List<Result>> {
        val currentQuoteList = apiInterface.getQuotes(page)
        var result: List<Result> = listOf()

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