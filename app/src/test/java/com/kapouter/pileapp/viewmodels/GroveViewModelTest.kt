package com.kapouter.pileapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.utils.observeOnce
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.Matchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GroveViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GroveViewModel

    private val plant1 = GrovePlant(1, "plant1", "Plant1.0", null, null, null, null)
    private val plant2 = GrovePlant(2, "plant2", "Plant2.0", null, null, null, null)


    private val groveRepository = mock<GroveRepository> {
        on { getPlants() } doReturn LivePagedListBuilder<Int, GrovePlant>(object :
            DataSource.Factory<Int, GrovePlant>() {
            override fun create(): DataSource<Int, GrovePlant> = object : PageKeyedDataSource<Int, GrovePlant>() {
                override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GrovePlant>) {
                }

                override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GrovePlant>) {
                    callback.onResult(listOf(plant1, plant2), null)
                }

                override fun loadInitial(
                    params: LoadInitialParams<Int>,
                    callback: LoadInitialCallback<Int, GrovePlant>
                ) {
                    callback.onResult(listOf(plant1, plant2), null, null)
                }

            }

        }, 10).build()
    }

    @Before
    fun setup() {
        viewModel = GroveViewModel(groveRepository)
    }

    @Test
    fun testGetPlants() {
        viewModel.getPlants().observeOnce {
            assertThat(it, hasItems(plant1, plant2))
        }
    }
}