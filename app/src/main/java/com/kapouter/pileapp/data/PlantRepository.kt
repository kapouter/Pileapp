package com.kapouter.pileapp.data

import android.content.Context
import androidx.paging.LivePagedListBuilder
import com.kapouter.pileapp.db.AppDatabase
import com.kapouter.pileapp.model.PlantsResult
import com.kapouter.pileapp.services.TrefleService
import com.kapouter.pileapp.utils.PLANT_PAGE_SIZE
import java.util.concurrent.Executor


class PlantRepository(
    private val context: Context,
    private val trefleService: TrefleService,
    private val executor: Executor
) {

    fun getPlants(query: String): PlantsResult {
        val dataSource = AppDatabase.getInstance(context).plantDao()
        val dataSourceFactory = if (query.isNotEmpty()) dataSource.searchPlants(query) else dataSource.getPlants()
        val boundaryCallback = PlantBoundaryCallback(query, trefleService, dataSource, executor)
        val error = boundaryCallback.error
        val data = LivePagedListBuilder(dataSourceFactory, PLANT_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return PlantsResult(data, error)
    }
}