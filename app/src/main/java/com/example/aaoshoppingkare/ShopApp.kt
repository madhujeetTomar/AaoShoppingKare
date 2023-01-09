package com.example.aaoshoppingkare

import android.app.Application
import com.example.aaoshoppingkare.di.AppComponent
import com.example.aaoshoppingkare.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopApp : Application(){

    lateinit var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()

    }
}