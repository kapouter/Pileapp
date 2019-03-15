package com.kapouter.pileapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kapouter.pileapp.db.AppDatabase
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.utils.PLANT_PAGE_SIZE
import java.util.concurrent.Executor

class GroveRepository(private val context: Context, private val executor: Executor) {

    fun getPlants(): LiveData<PagedList<GrovePlant>> {
        val dataSource = AppDatabase.getInstance(context).groveDao()
        val dataSourceFactory = dataSource.getPlants()
        return LivePagedListBuilder(dataSourceFactory, PLANT_PAGE_SIZE)
            .build()
    }

    fun addPlant(plant: GrovePlant) {
        val dataSource = AppDatabase.getInstance(context).groveDao()
        executor.execute {
            dataSource.insert(plant)
        }
    }
}