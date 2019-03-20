package com.kapouter.pileapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.data.PlantRepository
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.model.PlantsResult
import javax.inject.Inject

class AddPlantViewModel @Inject constructor(
    private val repository: PlantRepository,
    private val groveRepository: GroveRepository
) : ViewModel() {

    private val query: MutableLiveData<String> = MutableLiveData("")
    private val plantsResult: LiveData<PlantsResult> =
        Transformations.map(query) { repository.getPlants(it) }
    private val plants: LiveData<PagedList<Plant>> =
        Transformations.switchMap(plantsResult) { it.data }
    private val error: LiveData<String> =
        Transformations.switchMap(plantsResult) { it.error }

    fun getPlants(): LiveData<PagedList<Plant>> = plants
    fun getError(): LiveData<String> = error

    fun searchPlants(q: String = "") {
        query.postValue(q)
    }

    fun addPlant(plantId: Int) {
        groveRepository.addPlant(plantId)
    }
}