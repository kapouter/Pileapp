package com.kapouter.pileapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context, private val plantId: Int? = null) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AddPlantViewModel::class.java) -> @Suppress("UNCHECKED_CAST")
            return AddPlantViewModel(context) as T

            modelClass.isAssignableFrom(GroveViewModel::class.java) -> @Suppress("UNCHECKED_CAST")
            return GroveViewModel(context) as T

            modelClass.isAssignableFrom(PlantDetailViewModel::class.java) -> {
                if (plantId == null) throw IllegalArgumentException("plantId should not be null")
                @Suppress("UNCHECKED_CAST")
                return PlantDetailViewModel(context, plantId) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}