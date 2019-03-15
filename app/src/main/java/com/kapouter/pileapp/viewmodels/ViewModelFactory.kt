package com.kapouter.pileapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPlantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddPlantViewModel(context) as T
        } else if (modelClass.isAssignableFrom(GroveViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroveViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}