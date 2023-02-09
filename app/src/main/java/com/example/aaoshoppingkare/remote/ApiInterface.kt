package com.example.aaoshoppingkare.remote

import com.example.aaoshoppingkare.model.QuoteList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/quotes")

    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>
}