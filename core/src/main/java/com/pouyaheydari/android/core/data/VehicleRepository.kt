package com.pouyaheydari.android.core.data

class VehicleRepository constructor(private val vehicleDataSource: VehicleDataSource) {

    suspend fun getAllVehicles() = vehicleDataSource.getAllVehicles()

    suspend fun getVehicleById(vehicleId: Int) = vehicleDataSource.getVehicleById(vehicleId)

    suspend fun reserveVehicle(vehicleId: Int) = vehicleDataSource.reserveVehicle(vehicleId)
}

