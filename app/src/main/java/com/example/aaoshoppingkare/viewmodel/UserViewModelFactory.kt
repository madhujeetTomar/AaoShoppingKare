package com.example.aaoshoppingkare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aaoshoppingkare.di.ActivityScope
import com.example.aaoshoppingkare.repository.UserRepository
import javax.inject.Inject

@ActivityScope
class UserViewModelFactory @Inject constructor(private val repository: UserRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}