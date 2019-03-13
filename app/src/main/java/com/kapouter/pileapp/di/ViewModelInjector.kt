package com.kapouter.pileapp.di

import com.kapouter.pileapp.viewmodels.AddPlantViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ServiceModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param addPlantViewModel AddPlantViewModel in which to inject the dependencies
     */
    fun inject(addPlantViewModel: AddPlantViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun serviceModule(serviceModule: ServiceModule): Builder
    }
}