package com.kapouter.pileapp.di

import android.content.Context
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.data.PlantRepository
import com.kapouter.pileapp.db.AppDatabase
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
        PlantRepository(AppDatabase.getInstance(context).plantDao(), trefleService, executor)

    @Provides
    fun getGroveRepository(trefleService: TrefleService, executor: Executor): GroveRepository =
        GroveRepository(
            AppDatabase.getInstance(context).groveDao(),
            AppDatabase.getInstance(context).plantDao(),
            trefleService,
            executor
        )
}