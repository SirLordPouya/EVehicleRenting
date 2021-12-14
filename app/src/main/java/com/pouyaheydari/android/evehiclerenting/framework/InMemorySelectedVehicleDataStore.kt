package com.pouyaheydari.android.evehiclerenting.framework

import com.pouyaheydari.android.core.data.SelectedVehicleDataStore
import com.pouyaheydari.android.evehiclerenting.presentation.utils.NO_CAR_SELECTED

class InMemorySelectedVehicleDataStore : SelectedVehicleDataStore {

    var vehicleId = NO_CAR_SELECTED

    override fun setSelectedVehicleId(vehicleId: Int) {
        this.vehicleId = vehicleId
    }

    override fun getSelectedVehicleId(): Int = vehicleId
}