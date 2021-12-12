package com.pouyaheydari.android.core.domain

data class VehicleDetails(
    val address: String,
    val carId: Int,
    val city: String,
    val damageDescription: String,
    val fuelLevel: Int,
    val hardwareId: String,
    val isActivatedByHardware: Boolean,
    val isClean: Boolean,
    val isDamaged: Boolean,
    val lat: Double,
    val licencePlate: String,
    val locationId: Int,
    val lon: Double,
    val pricingParking: String,
    val pricingTime: String,
    val reservationState: Int,
    var title: String,
    val vehicleStateId: Int,
    val vehicleTypeId: Int,
    val vehicleTypeImageUrl: String,
    val zipCode: String
)