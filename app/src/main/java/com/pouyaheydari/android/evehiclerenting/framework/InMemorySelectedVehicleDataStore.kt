package com.pouyaheydari.android.evehiclerenting.framework

import com.pouyaheydari.android.core.data.SelectedVehicleDataStore

class InMemorySelectedVehicleDataStore : SelectedVehicleDataStore {

    var vehicleId = -1

    override fun setSelectedVehicleId(vehicleId: Int) {
        this.vehicleId = vehicleId
    }

    override fun getSelectedVehicleId(): Int = vehicleId
}