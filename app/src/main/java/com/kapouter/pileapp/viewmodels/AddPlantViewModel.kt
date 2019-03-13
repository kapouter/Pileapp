package com.kapouter.pileapp.viewmodels

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kapouter.pileapp.data.Plant
import com.kapouter.pileapp.data.PlantRepository
import javax.inject.Inject

class AddPlantViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: PlantRepository

    private val query: MutableLiveData<String> = MutableLiveData("")
    private val plants: LiveData<List<Plant>> =
        Transformations.switchMap(query, Function { query -> repository.getPlants(query) })

    fun getPlants(): LiveData<List<Plant>> = plants

    fun searchPlants(q: String = "") {
        query.postValue(q)
    }

    fun addPlant(plant: Plant) {

    }
}