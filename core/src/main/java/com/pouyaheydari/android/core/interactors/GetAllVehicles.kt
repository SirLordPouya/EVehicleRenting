package com.pouyaheydari.android.core.interactors

import com.pouyaheydari.android.core.data.VehicleDataSource
import javax.inject.Inject

class GetAllVehicles @Inject constructor(private val vehicleDataSource: VehicleDataSource) {
    suspend operator fun invoke() = vehicleDataSource.getAllVehicles()
}