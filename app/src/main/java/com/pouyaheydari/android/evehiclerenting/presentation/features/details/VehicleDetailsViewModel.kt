package com.pouyaheydari.android.evehiclerenting.presentation.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.domain.ReserveRequest
import com.pouyaheydari.android.core.interactors.GetRemoteVehicleById
import com.pouyaheydari.android.core.interactors.GetSelectedVehicleId
import com.pouyaheydari.android.core.interactors.ReserveVehicleById
import com.pouyaheydari.android.evehiclerenting.presentation.features.details.DetailsDataResource.*
import com.pouyaheydari.android.evehiclerenting.presentation.utils.NO_TITLE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    getSelectedVehicleId: GetSelectedVehicleId,
    private val getRemoteVehicleById: GetRemoteVehicleById,
    private val reserveVehicleById: ReserveVehicleById,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    ViewModel() {

    private val _uiStatusLiveData = MutableLiveData<DetailsDataResource>()
    val uiStatusLiveData: LiveData<DetailsDataResource> = _uiStatusLiveData
    private val vehicleId = getSelectedVehicleId()

    fun onViewLoaded() {
        viewModelScope.launch(dispatcher) {
            runCatching {
                _uiStatusLiveData.postValue(Loading)
                val response = getRemoteVehicleById(vehicleId)
                _uiStatusLiveData.postValue(VehicleDetailsReceived(response).also {
                    if (it.vehicleDetails?.title.isNullOrEmpty()) it.vehicleDetails?.title =
                        NO_TITLE
                })
            }.onFailure {
                _uiStatusLiveData.postValue(DataFetchFailure)
            }
        }
    }

    fun onReserveRequested() {
        viewModelScope.launch(dispatcher) {
            runCatching {
                reserveVehicleById(ReserveRequest(vehicleId))
                _uiStatusLiveData.postValue(RentalSuccess)
            }.onFailure {
                _uiStatusLiveData.postValue(DataFetchFailure)
            }
        }
    }
}