package com.kapouter.pileapp.di

import android.content.Context
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.data.PlantRepository
import com.kapouter.pileapp.services.TrefleService
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
class RepositoryModule(val context: Context) {

    @Provides
    fun getExecutor(): Executor = Executors.newSingleThreadExecutor()

    @Provides
    fun getPlantRepository(trefleService: TrefleService, executor: Executor): PlantRepository =
        PlantRepository(context, trefleService, executor)

    @Provides
    fun getGroveRepository(trefleService: TrefleService, executor: Executor): GroveRepository =
        GroveRepository(context, trefleService, executor)
}