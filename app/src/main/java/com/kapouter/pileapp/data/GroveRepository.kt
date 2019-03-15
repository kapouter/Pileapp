package com.kapouter.pileapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kapouter.pileapp.db.AppDatabase
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.services.TrefleService
import com.kapouter.pileapp.utils.PLANT_PAGE_SIZE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class GroveRepository(
    private val context: Context,
    private val trefleService: TrefleService,
    private val executor: Executor
) {

    fun getPlants(): LiveData<PagedList<GrovePlant>> {
        val dataSource = AppDatabase.getInstance(context).groveDao()
        val dataSourceFactory = dataSource.getPlants()
        return LivePagedListBuilder(dataSourceFactory, PLANT_PAGE_SIZE)
            .build()
    }

    fun addPlant(plantId: Int) {
        val dataSource = AppDatabase.getInstance(context).groveDao()
        trefleService.getPlant(plantId).enqueue(object : Callback<GrovePlant> {

            override fun onFailure(call: Call<GrovePlant>, t: Throwable) {
            }

            override fun onResponse(call: Call<GrovePlant>, response: Response<GrovePlant>) {
                if (response.isSuccessful) {
                    if (response.body() != null)
                        executor.execute {
                            dataSource.insert(response.body() as GrovePlant)
                        }
                }
            }
        })
    }
}