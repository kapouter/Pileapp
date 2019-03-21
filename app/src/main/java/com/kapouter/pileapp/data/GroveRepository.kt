package com.kapouter.pileapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kapouter.pileapp.db.GroveDao
import com.kapouter.pileapp.db.PlantDao
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.services.TrefleService
import com.kapouter.pileapp.utils.PLANT_PAGE_SIZE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class GroveRepository(
    private val groveDao: GroveDao,
    private val plantDao: PlantDao,
    private val trefleService: TrefleService,
    private val executor: Executor
) {

    fun getPlants(): LiveData<PagedList<GrovePlant>> {
        val dataSourceFactory = groveDao.getPlants()
        return LivePagedListBuilder(dataSourceFactory, PLANT_PAGE_SIZE)
            .build()
    }

    fun getPlant(plantId: Int): LiveData<GrovePlant> {
        val data = MutableLiveData<GrovePlant>()
        executor.execute {
            data.postValue(groveDao.getPlant(plantId))
        }

        trefleService.getPlant(plantId).enqueue(object : Callback<GrovePlant> {
            override fun onFailure(call: Call<GrovePlant>, t: Throwable) {
            }

            override fun onResponse(call: Call<GrovePlant>, response: Response<GrovePlant>) {
                if (response.isSuccessful) {
                    val plant = response.body()
                    if (plant != null) {
                        data.postValue(plant)
                        executor.execute {
                            groveDao.update(plant)
                        }
                    }
                }
            }
        })

        return data
    }

    fun addPlant(plantId: Int) {
        trefleService.getPlant(plantId).enqueue(object : Callback<GrovePlant> {

            override fun onFailure(call: Call<GrovePlant>, t: Throwable) {
            }

            override fun onResponse(call: Call<GrovePlant>, response: Response<GrovePlant>) {
                if (response.isSuccessful) {
                    val plant = response.body()
                    if (plant != null)
                        executor.execute {
                            groveDao.insert(plant)
                            plantDao.applyIsInGrove(plant.id)
                        }
                }
            }
        })
    }
}