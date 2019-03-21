package com.kapouter.pileapp.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.toLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.testUtils.observeOnce
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GroveDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var groveDao: GroveDao

    private val plant1 = GrovePlant(1, "plant1", "Plant1.0", null, null, null, null)
    private val plant2 = GrovePlant(2, "plant2", "Plant2.0", null, null, null, null)
    private val plant3 = GrovePlant(3, "plant3", "Plant3.0", null, null, null, null)
    private val plant4 = GrovePlant(4, "plant4", "Plant4.0", null, null, null, null)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        groveDao = database.groveDao()

        groveDao.insert(plant1)
        groveDao.insert(plant2)
        groveDao.insert(plant3)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testInsertPlant() {
        groveDao.insert(plant4)

        val dataSource = groveDao.getPlants()

        dataSource.toLiveData(pageSize = 10).observeOnce {
            assertThat(it, hasItems(plant4))
        }
    }

    @Test
    fun testGetPlants() {
        val dataSource = groveDao.getPlants()

        dataSource.toLiveData(pageSize = 10).observeOnce {
            assertThat(it, hasItems(plant1, plant2, plant3))
        }
    }

    @Test
    fun testGetPlant() {
        groveDao.insert(plant1)
        val plant = groveDao.getPlant(1)
        assertThat(plant, equalTo(plant1))
    }
}