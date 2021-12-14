package com.pouyaheydari.android.evehiclerenting.framework

import com.pouyaheydari.android.core.data.VehicleDataSource
import com.pouyaheydari.android.core.domain.ReserveRequest
import com.pouyaheydari.android.core.domain.ReserveResponse
import com.pouyaheydari.android.core.domain.VehicleDetails
import com.pouyaheydari.android.core.domain.Vehicles
import retrofit2.http.*

interface RetrofitVehicleDataSource : VehicleDataSource {

    @GET("cars.json")
    override suspend fun getAllVehicles(): List<Vehicles>

    @GET("cars/{carId}")
    override suspend fun getVehicleById(@Path("carId") vehicleId: Int): VehicleDetails

    @POST("https://4i96gtjfia.execute-api.eu-central-1.amazonaws.com/default/wunderfleet-recruiting-mobile-dev-quick-rental")
    @Headers("Authorization: Bearer df7c313b47b7ef87c64c0f5f5cebd6086bbb0fa")
    override suspend fun reserveVehicle(@Body reserveRequest: ReserveRequest):ReserveResponse
}