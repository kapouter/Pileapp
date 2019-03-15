package com.kapouter.pileapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.kapouter.pileapp.data.PlantRepository
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.model.PlantsResult
import javax.inject.Inject

class AddPlantViewModel(context: Context) : BaseViewModel(context) {

    @Inject
    lateinit var repository: PlantRepository

    private val query: MutableLiveData<String> = MutableLiveData("")
    private val plantsResult: LiveData<PlantsResult> =
        Transformations.map(query) { repository.getPlants(it) }
    private val plants: LiveData<PagedList<Plant>> =
        Transformations.switchMap(plantsResult) { it.data }

    fun getPlants(): LiveData<PagedList<Plant>> = plants

    fun searchPlants(q: String = "") {
        query.postValue(q)
    }

    fun addPlant(plant: Plant) {
        //TODO: implement add plant query
    }
}