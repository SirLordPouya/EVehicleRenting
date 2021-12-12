package com.pouyaheydari.android.core.data

interface SelectedVehicleDataStore {

    fun setSelectedVehicleId(vehicleId: Int)

    fun getSelectedVehicleId(): Int
}