package com.kapouter.pileapp.services

import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.utils.PLANT_PAGE_SIZE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrefleService {

    @GET("plants")
    fun getPlants(
        @Query("q") query: String,
        @Query("page") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = PLANT_PAGE_SIZE
    ): Call<List<Plant>>


    @GET("plants/{id}")
    fun getPlant(@Path("id") id: Int): Call<GrovePlant>
}