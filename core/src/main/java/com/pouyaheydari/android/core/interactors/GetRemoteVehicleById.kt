package com.pouyaheydari.android.core.interactors

import com.pouyaheydari.android.core.data.VehicleDataSource
import javax.inject.Inject

class GetRemoteVehicleById @Inject constructor(private val vehicleDataSource: VehicleDataSource) {
    suspend operator fun invoke(vehicleId:Int) = vehicleDataSource.getVehicleById(vehicleId)
}