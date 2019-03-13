package com.kapouter.pileapp.viewmodels

import androidx.lifecycle.ViewModel
import com.kapouter.pileapp.di.DaggerViewModelInjector
import com.kapouter.pileapp.di.ViewModelInjector
import com.kapouter.pileapp.di.ServiceModule

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .serviceModule(ServiceModule())
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is AddPlantViewModel -> injector.inject(this)
        }
    }
}