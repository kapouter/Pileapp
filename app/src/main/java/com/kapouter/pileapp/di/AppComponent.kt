package com.kapouter.pileapp.di

import com.kapouter.pileapp.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(baseActivity: BaseActivity)
}