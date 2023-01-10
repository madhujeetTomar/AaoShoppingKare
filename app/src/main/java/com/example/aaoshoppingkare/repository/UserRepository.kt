package com.example.aaoshoppingkare.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aaoshoppingkare.model.Response
import com.example.aaoshoppingkare.model.UserRequest
import com.example.aaoshoppingkare.remote.ApiInterface
import java.lang.annotation.Inherited
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiInterface: ApiInterface) {

 val response = MutableLiveData<Response>()


    suspend fun signIn(userRequest: UserRequest) {
       val res = apiInterface.signIn(userRequest).body()
        response.value = res!!
    }
}