package com.pouyaheydari.android.evehiclerenting.presentation.features.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pouyaheydari.android.core.domain.Vehicles
import com.pouyaheydari.android.core.interactors.GetAllVehicles
import com.pouyaheydari.android.core.interactors.SetSelectedVehicleId
import com.pouyaheydari.android.evehiclerenting.presentation.features.map.MapsDataResource.CarFocused
import com.pouyaheydari.android.evehiclerenting.presentation.features.map.MapsDataResource.CarSelected
import com.pouyaheydari.android.evehiclerenting.presentation.utils.NO_TITLE
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule

class MapViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: MapViewModel

    private val vehiclesList = arrayListOf(
        Vehicles(
            "",
            1,
            "",
            "",
            1,
            false,
            false,
            0.0,
            "",
            1,
            0.0,
            "",
            "",
            1,
            "",
            1,
            1,
            ""
        ), Vehicles(
            "",
            2,
            "",
            "",
            1,
            false,
            false,
            0.0,
            "",
            1,
            0.0,
            "",
            "",
            1,
            "",
            1,
            1,
            ""
        )

    )


    private val coroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getAllVehicles: GetAllVehicles

    @MockK
    lateinit var setSelectedVehicleId: SetSelectedVehicleId

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createViewModel() {
        viewModel = MapViewModel(
            getAllVehicles, setSelectedVehicleId, coroutineDispatcher
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test when user selects a car for the first time then CarFocused must be published with only one car`() =
        coroutineDispatcher.runBlockingTest {

            coEvery { getAllVehicles() }.coAnswers { vehiclesList }
            createViewModel()
            viewModel.uiStatusLiveData.observeForever { }
            viewModel.getVehicles()

            viewModel.onCarClicked(1)

            val carId = viewModel.uiStatusLiveData.value?.selectedCar!!.carId
            val type = viewModel.uiStatusLiveData.value

            Assert.assertEquals(1, carId)
            Assert.assertEquals(CarFocused::class.java, type?.javaClass)
        }

    @Test
    fun `test when user selects a car for the second time then CarSelected must be published with only one car`() =
        coroutineDispatcher.runBlockingTest {

            coEvery { getAllVehicles() }.coAnswers { vehiclesList }
            coEvery { setSelectedVehicleId(1) }.coAnswers { }

            createViewModel()
            viewModel.uiStatusLiveData.observeForever { }
            viewModel.getVehicles()

            viewModel.onCarClicked(1)
            viewModel.onCarClicked(1)

            val carId = viewModel.uiStatusLiveData.value?.selectedCar!!.carId
            val type = viewModel.uiStatusLiveData.value

            Assert.assertEquals(1, carId)
            Assert.assertEquals(CarSelected::class.java, type?.javaClass)
        }

    @Test
    fun `test when fetching the list of vehicles then all vehicle must have a title`() =
        coroutineDispatcher.runBlockingTest {
            coEvery { getAllVehicles() }.coAnswers { vehiclesList }

            createViewModel()
            viewModel.uiStatusLiveData.observeForever { }
            viewModel.getVehicles()

            val result = viewModel.uiStatusLiveData.value?.vehicles?.first()?.title
            Assert.assertEquals(NO_TITLE, result)
        }

}