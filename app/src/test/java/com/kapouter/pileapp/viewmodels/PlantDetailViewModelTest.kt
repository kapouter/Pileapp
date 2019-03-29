package com.kapouter.pileapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.utils.observeOnce
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PlantDetailViewModel

    private val plantId = 1
    private val plant1 = GrovePlant(plantId, "plant1", "Plant1.0", null, null, null, null)

    private val groveRepository = mock<GroveRepository> {
        on { getPlant(plantId) } doReturn MutableLiveData(plant1)
    }

    @Before
    fun setup() {
        viewModel = PlantDetailViewModel(groveRepository)
    }

    @Test
    fun testLoadPlant() {
        viewModel.loadPlant(plantId)
        viewModel.getPlant().observeOnce {
            Assert.assertThat(it, Matchers.equalTo(plant1))
        }
    }

    @Test
    fun testRemovePlant() {
        viewModel.loadPlant(plantId)
        viewModel.getPlant().observeOnce {
            Assert.assertThat(it, Matchers.equalTo(plant1))
            viewModel.removePlant()
            verify(groveRepository).removePlant(plant1)
        }
    }
}