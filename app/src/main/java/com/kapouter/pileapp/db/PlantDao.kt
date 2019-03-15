package com.kapouter.pileapp.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kapouter.pileapp.model.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants WHERE instr(name, :query) > 0 OR instr(scientificName, :query) > 0")
    fun searchPlants(query: String): DataSource.Factory<Int, Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plants: List<Plant>)
}