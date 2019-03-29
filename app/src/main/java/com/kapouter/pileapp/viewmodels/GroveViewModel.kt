package com.kapouter.pileapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.model.GrovePlant
import javax.inject.Inject

class GroveViewModel @Inject constructor(repository: GroveRepository) : ViewModel() {

    private val plants: LiveData<PagedList<GrovePlant>> = repository.getPlants()

    fun getPlants(): LiveData<PagedList<GrovePlant>> = plants

}