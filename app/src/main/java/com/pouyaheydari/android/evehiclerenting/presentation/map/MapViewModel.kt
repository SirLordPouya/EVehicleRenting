package com.pouyaheydari.android.evehiclerenting.presentation.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.domain.Vehicles
import com.pouyaheydari.android.core.interactors.GetAllVehicles
import com.pouyaheydari.android.evehiclerenting.framework.utils.SingleLiveEvent
import com.pouyaheydari.android.evehiclerenting.presentation.map.MapsDataResource.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NOT_TITLE = "No Title"

@HiltViewModel
class MapViewModel @Inject constructor(private val getAllVehicles: GetAllVehicles) : ViewModel() {

    private val _uiStatusLiveData = SingleLiveEvent<MapsDataResource>()
    private val vehicles = arrayListOf<Vehicles>()
    private var selectedCarId = -1

    val uiStatusLiveData: SingleLiveEvent<MapsDataResource> = _uiStatusLiveData

    fun getVehicles() {
        viewModelScope.launch {
            _uiStatusLiveData.postValue(Loading)
            val response =
                getAllVehicles().onEach { if (it.title.isNullOrEmpty()) it.title = NOT_TITLE }
            vehicles.addAll(response)
            _uiStatusLiveData.postValue(AllVehiclesReceived(response))
        }
    }

    fun onCarClicked(carId: Int) {
        if (selectedCarId == carId) {
            _uiStatusLiveData.postValue(CarSelected(vehicles.first { it.carId == carId }))
            selectedCarId = -1
        } else {
            selectedCarId = carId
            _uiStatusLiveData.postValue(CarFocused(vehicles.first { it.carId == carId }))
        }
    }
}