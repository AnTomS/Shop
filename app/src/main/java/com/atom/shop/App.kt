package com.atom.shop

import android.app.Application
import android.util.Log
import com.atom.shop.di.AppComponent
import com.atom.shop.di.AppModule
import com.atom.shop.di.DaggerAppComponent
import com.atom.shop.di.DomainModule
import com.atom.shop.di.RoomModule
import com.atom.shop.di.ViewModelModule

class App : Application() {
    lateinit var appComponent : AppComponent

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()

            .appModule(AppModule(this))
            .domainModule(DomainModule())
            .viewModelModule(ViewModelModule())
            .roomModule(RoomModule(this))
            .build()

        Log.d("App", "AppComponent initialized: $appComponent")
    }
}