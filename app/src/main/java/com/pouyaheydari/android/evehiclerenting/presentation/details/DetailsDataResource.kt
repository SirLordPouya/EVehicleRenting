package com.pouyaheydari.android.evehiclerenting.presentation.details

import com.pouyaheydari.android.core.domain.VehicleDetails

sealed class DetailsDataResource(
    val vehicleDetails: VehicleDetails? = null,
) {

    object Loading : DetailsDataResource()

    object DataFetchFailure : DetailsDataResource()

    object RentalSuccess : DetailsDataResource()

    class VehicleDetailsReceived(vehicleDetails: VehicleDetails) :
        DetailsDataResource(vehicleDetails = vehicleDetails)
}
