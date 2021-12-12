package com.pouyaheydari.android.evehiclerenting.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.interactors.GetRemoteVehicleById
import com.pouyaheydari.android.core.interactors.GetSelectedVehicleId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    getSelectedVehicleId: GetSelectedVehicleId,
    getRemoteVehicleById: GetRemoteVehicleById
) :
    ViewModel() {

    init {
        val vehicleId = getSelectedVehicleId()
        viewModelScope.launch {
            val response = getRemoteVehicleById(vehicleId)
            println(response)
        }
    }
}