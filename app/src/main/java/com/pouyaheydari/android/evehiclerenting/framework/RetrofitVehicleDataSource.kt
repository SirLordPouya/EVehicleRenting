package com.pouyaheydari.android.evehiclerenting.framework

import com.pouyaheydari.android.core.data.VehicleDataSource
import com.pouyaheydari.android.core.domain.Vehicles
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitVehicleDataSource : VehicleDataSource {

    @GET("cars.json")
    override suspend fun getAllVehicles(): List<Vehicles>

    @GET("cars/{carId}")
    override suspend fun getVehicleById(@Path("carId") vehicleId: Int)
}