package com.kapouter.pileapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kapouter.pileapp.viewmodels.AddPlantViewModel
import com.kapouter.pileapp.viewmodels.GroveViewModel
import com.kapouter.pileapp.viewmodels.PlantDetailViewModel
import com.kapouter.pileapp.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// Using https://proandroiddev.com/how-to-better-unit-testing-42a956e005d6

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GroveViewModel::class)
    internal abstract fun bindGroveViewModel(groveViewModel: GroveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddPlantViewModel::class)
    internal abstract fun bindAddPlantViewModel(addPlantViewModel: AddPlantViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlantDetailViewModel::class)
    internal abstract fun bindPlantDetailViewModel(plantDetailViewModel: PlantDetailViewModel): ViewModel
}