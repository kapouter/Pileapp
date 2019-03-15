package com.kapouter.pileapp.di

import com.kapouter.pileapp.viewmodels.AddPlantViewModel
import com.kapouter.pileapp.viewmodels.GroveViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ServiceModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified AddPlantViewModel.
     * @param addPlantViewModel AddPlantViewModel in which to inject the dependencies
     */
    fun inject(addPlantViewModel: AddPlantViewModel)

    /**
     * Injects required dependencies into the specified GroveViewModel.
     * @param groveViewModel GroveViewModel in which to inject the dependencies
     */
    fun inject(groveViewModel: GroveViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun serviceModule(serviceModule: ServiceModule): Builder
    }
}