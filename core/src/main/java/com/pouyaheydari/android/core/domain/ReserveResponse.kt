package com.pouyaheydari.android.core.domain

data class ReserveResponse(
    val carId: Int,
    val cost: Int,
    val damageDescription: String,
    val drivenDistance: Int,
    val endTime: Int,
    val fuelCardPin: String,
    val isParkModeEnabled: Boolean,
    val licencePlate: String,
    val reservationId: Int,
    val startAddress: String,
    val startTime: Int,
    val userId: Int
)