package com.kapouter.pileapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.data.PlantRepository
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.model.PlantsResult
import com.kapouter.pileapp.utils.observeOnce
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.hamcrest.Matchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Using https://medium.com/@marco_cattaneo/unit-testing-with-mockito-on-kotlin-android-project-with-architecture-components-2059eb637912

@RunWith(AndroidJUnit4::class)
class AddPlantViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AddPlantViewModel

    private val plant1 = Plant(1, "plant1", "Plant1.0")
    private val plant2 = Plant(2, "plant2", "Plant2.0")
    private val plant3 = Plant(3, "plant3", "Plant3.0")
    private val plant4 = Plant(4, "plant4", "Plant4.0")

    private val plantRepository = mock<PlantRepository> {
        on { getPlants("plant1") } doReturn PlantsResult(
            LivePagedListBuilder<Int, Plant>(object :
                DataSource.Factory<Int, Plant>() {
                override fun create(): DataSource<Int, Plant> = object : PageKeyedDataSource<Int, Plant>() {
                    override fun loadInitial(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, Plant>
                    ) {
                        callback.onResult(listOf(plant1, plant2, plant3, plant4), null, null)
                    }

                    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Plant>) {
                        callback.onResult(listOf(plant1, plant2, plant3, plant4), null)
                    }

                    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Plant>) {
                    }

                }

            }, 10)
                .build(), MutableLiveData<String>()
        )
    }
    private val groveRepository = mock<GroveRepository> { }

    @Before
    fun setup() {
        viewModel = AddPlantViewModel(plantRepository, groveRepository)
    }

    @Test
    fun testSearchPlantsWithoutQuery() {
        viewModel.searchPlants("plant1")
        viewModel.getPlants().observeOnce {
            assertThat(it, hasItems(plant1, plant2, plant3, plant4))
        }
    }

    @Test
    fun testAddPlant() {
        viewModel.addPlant(1)
        verify(groveRepository).addPlant(1)
    }
}