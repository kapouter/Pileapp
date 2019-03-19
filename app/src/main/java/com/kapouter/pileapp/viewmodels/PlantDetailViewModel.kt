package com.kapouter.pileapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.model.GrovePlant
import javax.inject.Inject

class PlantDetailViewModel(val context: Context, plantId: Int) : BaseViewModel(context) {

    @Inject
    lateinit var repository: GroveRepository

    private val plant: LiveData<GrovePlant>

    init {
        plant = repository.getPlant(plantId)
    }

    fun getPlant(): LiveData<GrovePlant> = plant
}