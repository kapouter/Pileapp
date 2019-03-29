package com.kapouter.pileapp.db

import androidx.paging.DataSource
import androidx.room.*
import com.kapouter.pileapp.model.GrovePlant

@Dao
interface GroveDao {
    @Query("SELECT * FROM grovePlants")
    fun getPlants(): DataSource.Factory<Int, GrovePlant>

    @Query("SELECT * FROM grovePlants WHERE id = :plantId")
    fun getPlant(plantId: Int): GrovePlant?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(plant: GrovePlant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: GrovePlant)

    @Delete
    fun delete(plant: GrovePlant)
}