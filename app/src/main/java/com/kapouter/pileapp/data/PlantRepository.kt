package com.kapouter.pileapp.data

import androidx.paging.LivePagedListBuilder
import com.kapouter.pileapp.db.PlantDao
import com.kapouter.pileapp.model.PlantsResult
import com.kapouter.pileapp.services.TrefleService
import com.kapouter.pileapp.utils.PLANT_PAGE_SIZE
import java.util.concurrent.Executor


class PlantRepository(
    private val plantDao: PlantDao,
    private val trefleService: TrefleService,
    private val executor: Executor
) {

    fun getPlants(query: String): PlantsResult {
        val dataSourceFactory = plantDao.searchPlants(query)
        val boundaryCallback = PlantBoundaryCallback(query, trefleService, plantDao, executor)
        val error = boundaryCallback.error
        val data = LivePagedListBuilder(dataSourceFactory, PLANT_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return PlantsResult(data, error)
    }
}