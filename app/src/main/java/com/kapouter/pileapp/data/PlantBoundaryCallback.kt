package com.kapouter.pileapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.kapouter.pileapp.db.PlantDao
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.services.TrefleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class PlantBoundaryCallback(
    private val query: String,
    private val trefleService: TrefleService,
    private val plantDao: PlantDao,
    private val executor: Executor
) :
    PagedList.BoundaryCallback<Plant>() {
    private var loading = false
    private var nextPage = 1
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Plant) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (loading) return

        loading = true
        trefleService.getPlants(query = query, pageNumber = nextPage).enqueue(object : Callback<List<Plant>> {

            override fun onResponse(call: Call<List<Plant>>, response: Response<List<Plant>>) {
                if (response.isSuccessful) {
                    nextPage++
                    if (response.body() != null) executor.execute {
                        plantDao.insert(response.body()!!)
                        plantDao.applyIsInGrove()
                    }
                } else {
                    _error.postValue(response.errorBody()?.string() ?: "error")
                }
                loading = false
            }

            override fun onFailure(call: Call<List<Plant>>, t: Throwable) {
                _error.postValue(t.message)
                loading = false
            }
        })
    }
}