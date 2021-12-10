package com.pouyaheydari.android.core.data

import com.pouyaheydari.android.core.domain.Vehicles

interface VehicleDataSource {

    suspend fun getAllVehicles(): List<Vehicles>

    suspend fun getVehicleById(vehicleId: Int)

    suspend fun reserveVehicle(vehicleId: Int)
}