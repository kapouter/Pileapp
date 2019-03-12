package com.kapouter.pileapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kapouter.pileapp.data.Plant

class PlantListViewModel : ViewModel() {
    private val plants: LiveData<List<Plant>> = loadPlants()

    fun getPlants(): LiveData<List<Plant>> = plants

    private fun loadPlants(): LiveData<List<Plant>> {
        val plantList = listOf(Plant("1", "name1", "description1"), Plant("2", "name2", "description2"))
        return MutableLiveData(plantList)
    }
}