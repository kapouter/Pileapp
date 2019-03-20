package com.kapouter.pileapp

import android.app.Application
import com.kapouter.pileapp.di.AppComponent
import com.kapouter.pileapp.di.DaggerAppComponent
import com.kapouter.pileapp.di.RepositoryModule
import com.kapouter.pileapp.di.ServiceModule

class Pileapp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        this.appComponent = DaggerAppComponent.builder()
            .serviceModule(ServiceModule())
            .repositoryModule(RepositoryModule(applicationContext))
            .build()
    }
}