package com.example.aaoshoppingkare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaoshoppingkare.model.Response
import com.example.aaoshoppingkare.model.UserRequest
import com.example.aaoshoppingkare.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

     val userResponse : LiveData<Response>
    get() = repository.response

    private val error = MutableLiveData<String>()



    fun signIn(userRequest: UserRequest)
    {
        viewModelScope.launch {
            try {
                repository.signIn(userRequest)
            }
            catch (e: Exception)
            {
                error.value = e.message
            }

        }
    }
}