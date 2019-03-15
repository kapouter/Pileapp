package com.kapouter.pileapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.model.GrovePlant
import javax.inject.Inject

class GroveViewModel(val context: Context) : BaseViewModel(context) {

    @Inject
    lateinit var repository: GroveRepository

    private val plants: LiveData<PagedList<GrovePlant>> = repository.getPlants()

    fun getPlants(): LiveData<PagedList<GrovePlant>> = plants

}