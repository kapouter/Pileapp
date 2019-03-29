package com.kapouter.pileapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kapouter.pileapp.db.GroveDao
import com.kapouter.pileapp.db.PlantDao
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.services.TrefleService
import com.kapouter.pileapp.utils.observeOnce
import com.nhaarman.mockitokotlin2.*
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

// Using https://stackoverflow.com/questions/49854063/mocking-retrofit-response-calls-with-call-not-working
// and https://stackoverflow.com/questions/45160055/unit-testing-in-retrofit-for-callback

@RunWith(AndroidJUnit4::class)
class GroveRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val plant1 = GrovePlant(1, "plant1", "Plant1.0", null, null, null, null)

    private lateinit var repository: GroveRepository
    private val plantDao = mock<PlantDao>()
    private val groveDao = mock<GroveDao>()

    private val getPlantCall = mock<Call<GrovePlant>>()
    private val service = mock<TrefleService> {
        on { getPlant(1) } doReturn getPlantCall
    }
    private val executor = Executors.newSingleThreadExecutor()

    @Before
    fun setup() {
        repository = GroveRepository(groveDao, plantDao, service, executor)
    }

    @Test
    fun testGetPlant() {
        whenever(getPlantCall.enqueue(any())).doAnswer {
            val callback: Callback<GrovePlant> = it.getArgument(0)
            callback.onResponse(getPlantCall, Response.success(plant1))
        }
        val data = repository.getPlant(1)
        verify(groveDao).getPlant(1)
        verify(groveDao).update(plant1)
        data.observeOnce {
            assertThat(it, equalTo(plant1))
        }
    }

    @Test
    fun testAddPlant() {
        whenever(getPlantCall.enqueue(any())).doAnswer {
            val callback: Callback<GrovePlant> = it.getArgument(0)
            callback.onResponse(getPlantCall, Response.success(plant1))
        }
        repository.addPlant(1)
        verify(groveDao).insert(plant1)
        verify(plantDao).applyIsInGrove(1)
    }

    @Test
    fun testRemovePlant() {
        repository.removePlant(plant1)
        verify(groveDao).delete(plant1)
    }
}