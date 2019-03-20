package com.kapouter.pileapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.model.GrovePlant
import javax.inject.Inject

class PlantDetailViewModel @Inject constructor(private val repository: GroveRepository/*, plantId: Int*/) :
    ViewModel() {

    private val plantId = MutableLiveData<Int>()
    private val plant = Transformations.switchMap(plantId) { repository.getPlant(it) }

    fun getPlant(): LiveData<GrovePlant> = plant

    fun loadPlant(plantId: Int) {
        this.plantId.postValue(plantId)
    }
}