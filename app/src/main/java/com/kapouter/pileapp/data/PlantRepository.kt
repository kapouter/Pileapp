package com.kapouter.pileapp.data

import androidx.lifecycle.MutableLiveData
import com.kapouter.pileapp.services.TrefleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlantRepository(private val trefleService: TrefleService) {

    fun getPlants(query: String): MutableLiveData<List<Plant>> {
        val data = MutableLiveData<List<Plant>>()
        trefleService.getPlants(query = query).enqueue(object : Callback<List<Plant>> {

            override fun onResponse(call: Call<List<Plant>>, response: Response<List<Plant>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Plant>>, t: Throwable) {
            }
        })
        return data
    }
}