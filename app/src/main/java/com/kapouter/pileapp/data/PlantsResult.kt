package com.kapouter.pileapp.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class PlantsResult(val data: LiveData<PagedList<Plant>>, val error: LiveData<String>)