package com.kapouter.pileapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kapouter.pileapp.di.DaggerViewModelInjector
import com.kapouter.pileapp.di.ServiceModule
import com.kapouter.pileapp.di.ViewModelInjector

abstract class BaseViewModel(context: Context) : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .serviceModule(ServiceModule(context))
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is AddPlantViewModel -> injector.inject(this)
            is GroveViewModel -> injector.inject(this)
            is PlantDetailViewModel -> injector.inject(this)
        }
    }
}