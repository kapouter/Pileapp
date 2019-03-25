package com.kapouter.pileapp.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.toLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.utils.observeOnce
import org.hamcrest.CoreMatchers.hasItems
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var plantDao: PlantDao

    private val plant1 = Plant(1, "plant1", "Plant1.0")
    private val plant2 = Plant(2, "plant2", "Plant2.0")
    private val plant3 = Plant(3, "plant3", "Plant3.0")
    private val plant4 = Plant(4, "plant4", "Plant4.0")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        plantDao = database.plantDao()

        plantDao.insert(listOf(plant1, plant2, plant3, plant4))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSearchPlantsWithEmptyQuery() {
        val dataSource = plantDao.searchPlants("")
        dataSource.toLiveData(10).observeOnce {
            assertThat(it, hasItems(plant1, plant2, plant3, plant4))
        }
    }

    @Test
    fun testSearchPlantsWithQuery() {
        val dataSource = plantDao.searchPlants(plant1.name!!)
        dataSource.toLiveData(10).observeOnce {
            assertThat(it, hasItems(plant1))
        }
    }

}