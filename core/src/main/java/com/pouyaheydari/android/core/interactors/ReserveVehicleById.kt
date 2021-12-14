package com.pouyaheydari.android.core.interactors

import com.pouyaheydari.android.core.data.VehicleRepository
import com.pouyaheydari.android.core.domain.ReserveRequest
import javax.inject.Inject

class ReserveVehicleById @Inject constructor(private val repository: VehicleRepository) {
    suspend operator fun invoke(reserveRequest: ReserveRequest) =
        repository.reserveVehicle(reserveRequest)
}