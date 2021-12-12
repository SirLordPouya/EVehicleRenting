package com.pouyaheydari.android.core.data

import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val vehicleDataSource: VehicleDataSource,
    private val selectedVehicleDataStore: SelectedVehicleDataStore
) {

    suspend fun getAllVehicles() = vehicleDataSource.getAllVehicles()

    suspend fun getVehicleById(vehicleId: Int) = vehicleDataSource.getVehicleById(vehicleId)

    suspend fun reserveVehicle(vehicleId: Int) = vehicleDataSource.reserveVehicle(vehicleId)

    fun setSelectedVehicleId(vehicleId: Int) =
        selectedVehicleDataStore.setSelectedVehicleId(vehicleId)

    fun getSelectedVehicleId() = selectedVehicleDataStore.getSelectedVehicleId()
}

