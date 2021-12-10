package com.pouyaheydari.android.core.data

interface VehicleDataSource {

    suspend fun getAllVehicles()

    suspend fun getVehicleById(vehicleId: Int)

    suspend fun reserveVehicle(vehicleId: Int)
}