package com.pouyaheydari.android.core.interactors

import com.pouyaheydari.android.core.data.VehicleRepository
import javax.inject.Inject

class GetSelectedVehicleId @Inject constructor(private val repository: VehicleRepository) {
    operator fun invoke() = repository.getSelectedVehicleId()
}