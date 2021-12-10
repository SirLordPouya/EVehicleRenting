package com.pouyaheydari.android.evehiclerenting.presentation.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.domain.Vehicles
import com.pouyaheydari.android.core.interactors.GetAllVehicles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val getAllVehicles: GetAllVehicles) : ViewModel() {

    private val _vehiclesLiveData = MutableLiveData<List<Vehicles>>()
    val vehiclesLiveData: LiveData<List<Vehicles>> = _vehiclesLiveData

    fun getVehicles() {
        viewModelScope.launch {
            val response = getAllVehicles()
            _vehiclesLiveData.postValue(response)
        }
    }
}