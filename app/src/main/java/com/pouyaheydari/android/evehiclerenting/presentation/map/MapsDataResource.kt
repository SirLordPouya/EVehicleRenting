package com.pouyaheydari.android.evehiclerenting.presentation.map

import com.pouyaheydari.android.core.domain.Vehicles

sealed class MapsDataResource(
    val vehicles: List<Vehicles>? = null,
    val selectedCar: Vehicles? = null
) {

    object Loading : MapsDataResource()

    object DataFetchFailure : MapsDataResource()

    class AllVehiclesReceived(vehicles: List<Vehicles>) : MapsDataResource(vehicles = vehicles)

    class CarSelected(selectedCar: Vehicles) : MapsDataResource(selectedCar = selectedCar)

    class CarFocused(selectedCar: Vehicles) : MapsDataResource(selectedCar = selectedCar)
}
