package com.kapouter.pileapp.services

import com.kapouter.pileapp.BuildConfig
import com.kapouter.pileapp.data.Plant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrefleService {

    @GET("plants")
    fun getPlants(@Query("token") token: String = BuildConfig.TREFLE_TOKEN, @Query("q") query: String): Call<List<Plant>>


    @GET("plants/{id}")
    fun getPlant(@Query("token") token: String = BuildConfig.TREFLE_TOKEN, @Path("id") id: Int): Call<Plant>
}