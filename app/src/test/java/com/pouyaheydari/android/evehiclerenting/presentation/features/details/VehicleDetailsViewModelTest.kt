package com.pouyaheydari.android.evehiclerenting.presentation.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pouyaheydari.android.core.domain.ReserveRequest
import com.pouyaheydari.android.core.domain.ReserveResponse
import com.pouyaheydari.android.core.domain.VehicleDetails
import com.pouyaheydari.android.core.interactors.GetRemoteVehicleById
import com.pouyaheydari.android.core.interactors.GetSelectedVehicleId
import com.pouyaheydari.android.core.interactors.ReserveVehicleById
import com.pouyaheydari.android.evehiclerenting.presentation.utils.NO_TITLE
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule

class VehicleDetailsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: VehicleDetailsViewModel

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getSelectedVehicleId: GetSelectedVehicleId

    @MockK
    lateinit var getRemoteVehicleById: GetRemoteVehicleById

    @MockK
    lateinit var reserveVehicleById: ReserveVehicleById

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { getSelectedVehicleId() }.answers { 1 }
    }

    fun createViewModel() {
        viewModel = VehicleDetailsViewModel(
            getSelectedVehicleId,
            getRemoteVehicleById,
            reserveVehicleById,
            coroutineDispatcher
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test when fetching the list of vehicles then all vehicle must have a title`() =
        coroutineDispatcher.runBlockingTest {
            coEvery { getRemoteVehicleById(1) }.coAnswers {
                VehicleDetails(
                    "Bissenkamp 4",
                    1,
                    "Dortmund",
                    "Marius hat gesagt das muss so, und so, und so\r\nUnd wenn ich das verändere?",
                    27,
                    "HARDWARE",
                    true,
                    true,
                    true,
                    0.0,
                    "FBL 081",
                    2,
                    0.0,
                    "0,05/min",
                    "0,22/km",
                    0,
                    "",
                    0,
                    0,
                    "",
                    ""
                )
            }
            createViewModel()
            viewModel.uiStatusLiveData.observeForever { }
            viewModel.onViewLoaded()

            val result = viewModel.uiStatusLiveData.value?.vehicleDetails?.title
            Assert.assertEquals(NO_TITLE, result)
        }

    @Test
    fun `test when user reserves the vehicle then the view will be notified by live data`() =
        coroutineDispatcher.runBlockingTest {

            coEvery { reserveVehicleById(ReserveRequest(1)) }.coAnswers {
                ReserveResponse(
                    1,
                    100,
                    "",
                    1,
                    1,
                    "",
                    false,
                    "",
                    1,
                    "",
                    1,
                    2
                )
            }
            createViewModel()
            viewModel.uiStatusLiveData.observeForever {}

            viewModel.onReserveRequested()

            val result = viewModel.uiStatusLiveData.value

            Assert.assertEquals(DetailsDataResource.RentalSuccess, result)
        }
}