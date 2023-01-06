package com.example.aaoshoppingkare.di

import com.example.aaoshoppingkare.remote.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit
    {
     return Retrofit.Builder().baseUrl("https://quotable.io/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiInterface
    {
        return retrofit.create(ApiInterface::class.java)
    }

}