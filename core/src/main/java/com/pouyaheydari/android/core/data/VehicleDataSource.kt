package com.pouyaheydari.android.core.data

import com.pouyaheydari.android.core.domain.ReserveRequest
import com.pouyaheydari.android.core.domain.ReserveResponse
import com.pouyaheydari.android.core.domain.VehicleDetails
import com.pouyaheydari.android.core.domain.Vehicles

interface VehicleDataSource {

    suspend fun getAllVehicles(): List<Vehicles>

    suspend fun getVehicleById(vehicleId: Int): VehicleDetails

    suspend fun reserveVehicle(reserveRequest: ReserveRequest): ReserveResponse
}