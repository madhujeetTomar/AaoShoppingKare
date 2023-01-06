package com.example.aaoshoppingkare.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.model.Result
import com.example.aaoshoppingkare.remote.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val apiInterface: ApiInterface) {

    private val _quoteData = MutableLiveData<QuoteList>()
    val quoteData : LiveData<QuoteList>
    get() = _quoteData


suspend fun getQuotes(page: Int)
{
    val quotes = apiInterface.getQuotes(page)

    if(quotes.isSuccessful)
    {
        _quoteData.value = quotes.body()

    }

}
}