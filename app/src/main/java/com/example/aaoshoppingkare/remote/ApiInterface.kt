package com.example.aaoshoppingkare.remote

import com.example.aaoshoppingkare.model.QuoteList
import com.example.aaoshoppingkare.model.UserRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    suspend fun signIn(@Body userData : UserRequest) : Response<com.example.aaoshoppingkare.model.Response>
}