package com.kapouter.pileapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.services.TrefleService

/**
 * Use this way in view model
 *
 * @Inject
 * lateinit var service: TrefleService
 * @Inject
 * lateinit var executor: Executor
 * private val dataSourceFactory = PlantDataSourceFactory(service)
 * val plants: LiveData<PagedList<Plant>> =
 *     dataSourceFactory.toLiveData(pageSize = PLANT_PAGE_SIZE, fetchExecutor = executor)
 *
 * fun searchPlants(q: String = "") {
 *     dataSourceFactory.search(q)
 *     plants.value?.dataSource?.invalidate()
 * }
 */

class PlantDataSourceFactory(private val service: TrefleService) : DataSource.Factory<Int, Plant>() {
    private val sourceLiveData = MutableLiveData<PlantDataSource>()
    private var latestSource: PlantDataSource? = null
    private var query = ""

    override fun create(): DataSource<Int, Plant> {
        latestSource = PlantDataSource(service, query)
        sourceLiveData.postValue(latestSource)
        return latestSource as PlantDataSource
    }

    fun search(q: String) {
        query = q
    }
}