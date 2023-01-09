package com.example.aaoshoppingkare.di

import com.example.aaoshoppingkare.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}