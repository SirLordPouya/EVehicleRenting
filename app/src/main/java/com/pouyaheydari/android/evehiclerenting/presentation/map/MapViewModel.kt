package com.pouyaheydari.android.evehiclerenting.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.domain.Vehicles
import com.pouyaheydari.android.core.interactors.GetAllVehicles
import com.pouyaheydari.android.core.interactors.SetSelectedVehicleId
import com.pouyaheydari.android.evehiclerenting.framework.utils.NO_TITLE
import com.pouyaheydari.android.evehiclerenting.framework.utils.SingleLiveEvent
import com.pouyaheydari.android.evehiclerenting.presentation.map.MapsDataResource.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getAllVehicles: GetAllVehicles,
    private val setSelectedVehicleId: SetSelectedVehicleId
) : ViewModel() {

    private val vehicles = arrayListOf<Vehicles>()
    private var selectedCarId = -1

    val uiStatusLiveData = SingleLiveEvent<MapsDataResource>()

    fun getVehicles() {
        viewModelScope.launch {
            uiStatusLiveData.postValue(Loading)
            val response =
                getAllVehicles().onEach { if (it.title.isNullOrEmpty()) it.title = NO_TITLE }
            vehicles.addAll(response)
            uiStatusLiveData.postValue(AllVehiclesReceived(response))
        }
    }

    fun onCarClicked(carId: Int) {
        if (selectedCarId == carId) {
            setSelectedVehicleId(carId)
            uiStatusLiveData.postValue(CarSelected(vehicles.first { it.carId == carId }))
            selectedCarId = -1
        } else {
            selectedCarId = carId
            uiStatusLiveData.postValue(CarFocused(vehicles.first { it.carId == carId }))
        }
    }
}