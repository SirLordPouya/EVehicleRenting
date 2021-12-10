package com.pouyaheydari.android.core.interactors

import com.pouyaheydari.android.core.data.VehicleDataSource

class GetAllVehicles constructor(private val vehicleDataSource: VehicleDataSource) {
    suspend operator fun invoke() = vehicleDataSource.getAllVehicles()
}