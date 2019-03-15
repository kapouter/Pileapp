package com.kapouter.pileapp.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class PlantsResult(val data: LiveData<PagedList<Plant>>, val error: LiveData<String>)