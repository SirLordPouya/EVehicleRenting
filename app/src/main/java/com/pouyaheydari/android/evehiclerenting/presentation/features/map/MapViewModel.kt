package com.pouyaheydari.android.evehiclerenting.presentation.features.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.domain.Vehicles
import com.pouyaheydari.android.core.interactors.GetAllVehicles
import com.pouyaheydari.android.core.interactors.SetSelectedVehicleId
import com.pouyaheydari.android.evehiclerenting.presentation.utils.NO_CAR_SELECTED
import com.pouyaheydari.android.evehiclerenting.presentation.utils.NO_TITLE
import com.pouyaheydari.android.evehiclerenting.presentation.utils.SingleLiveEvent
import com.pouyaheydari.android.evehiclerenting.presentation.features.map.MapsDataResource.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getAllVehicles: GetAllVehicles,
    private val setSelectedVehicleId: SetSelectedVehicleId,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val vehicles = arrayListOf<Vehicles>()
    private var selectedCarId = NO_CAR_SELECTED

    val uiStatusLiveData = SingleLiveEvent<MapsDataResource>()

    fun getVehicles() {
        viewModelScope.launch(dispatcher) {
            runCatching {
                uiStatusLiveData.postValue(Loading)
                val response =
                    getAllVehicles().onEach { if (it.title.isNullOrEmpty()) it.title = NO_TITLE }
                vehicles.addAll(response)
                uiStatusLiveData.postValue(AllVehiclesReceived(response))
            }.onFailure {
                uiStatusLiveData.postValue(DataFetchFailure)
            }
        }
    }

    fun onCarClicked(carId: Int) {
        if (selectedCarId == carId) {
            setSelectedVehicleId(carId)
            uiStatusLiveData.postValue(CarSelected(vehicles.first { it.carId == carId }))
            selectedCarId = NO_CAR_SELECTED
        } else {
            selectedCarId = carId
            uiStatusLiveData.postValue(CarFocused(vehicles.first { it.carId == carId }))
        }
    }
}