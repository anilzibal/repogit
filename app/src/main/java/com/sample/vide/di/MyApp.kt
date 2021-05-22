package com.sample.vide.di

import android.app.Application
import com.sample.vide.di.component.AppComponent
import com.sample.vide.di.component.DaggerAppComponent
import com.sample.vide.di.module.NetworkModule
import com.sample.vide.di.module.RoomModule

class MyApp : Application(){

    companion object{ lateinit var appComponent: AppComponent }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule(this))
            .roomModule(RoomModule(this))
            .build()
    }
}