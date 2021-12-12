package com.pouyaheydari.android.evehiclerenting.presentation.details

import androidx.lifecycle.ViewModel
import com.pouyaheydari.android.core.interactors.GetSelectedVehicleId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(getSelectedVehicleId: GetSelectedVehicleId) :
    ViewModel() {

        init {
            val vehicleId = getSelectedVehicleId()
        }
}