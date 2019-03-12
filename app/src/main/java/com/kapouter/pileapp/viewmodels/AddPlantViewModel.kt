package com.kapouter.pileapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kapouter.pileapp.data.Plant

class AddPlantViewModel : ViewModel() {
    private val plants: MutableLiveData<List<Plant>> = loadPlants()

    fun getPlants(): LiveData<List<Plant>> = plants

    private fun loadPlants(): MutableLiveData<List<Plant>> {
        val plantList = listOf(Plant("2", "name2", "description2"), Plant("4", "name4", "description4"))
        return MutableLiveData(plantList)
    }

    fun searchPlants(query: String = "") {
        val plantList = listOf(Plant("2", "name2", "description2"))
        plants.value = plantList
    }

    fun addPlant(plant: Plant) {

    }
}