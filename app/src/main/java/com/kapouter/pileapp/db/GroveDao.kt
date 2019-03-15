package com.kapouter.pileapp.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kapouter.pileapp.model.GrovePlant

@Dao
interface GroveDao {
    @Query("SELECT * FROM grovePlants")
    fun getPlants(): DataSource.Factory<Int, GrovePlant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: GrovePlant)
}