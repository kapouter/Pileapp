package com.kapouter.pileapp.db

import androidx.paging.DataSource
import androidx.room.*
import com.kapouter.pileapp.model.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants WHERE instr(name, :query) > 0 OR instr(scientificName, :query) > 0")
    fun searchPlants(query: String): DataSource.Factory<Int, Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plants: List<Plant>)

    @Query("UPDATE plants SET isGrovePlant = 1 WHERE id IN (SELECT id from plants INTERSECT SELECT id from grovePlants)")
    fun applyIsInGrove()

    @Query("UPDATE plants SET isGrovePlant = 1 WHERE id LIKE :plantId")
    fun applyIsInGrove(plantId: Int)

    @Query("SELECT * FROM plants WHERE id IN (SELECT id from plants INTERSECT SELECT id from grovePlants)")
    fun getIntersect(): List<Plant>
}