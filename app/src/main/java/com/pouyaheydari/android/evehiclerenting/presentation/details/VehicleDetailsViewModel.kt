package com.pouyaheydari.android.evehiclerenting.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.android.core.interactors.GetRemoteVehicleById
import com.pouyaheydari.android.core.interactors.GetSelectedVehicleId
import com.pouyaheydari.android.evehiclerenting.framework.utils.NO_TITLE
import com.pouyaheydari.android.evehiclerenting.presentation.details.DetailsDataResource.Loading
import com.pouyaheydari.android.evehiclerenting.presentation.details.DetailsDataResource.VehicleDetailsReceived
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    private val getSelectedVehicleId: GetSelectedVehicleId,
    private val getRemoteVehicleById: GetRemoteVehicleById
) :
    ViewModel() {

    private val _uiStatusLiveData = MutableLiveData<DetailsDataResource>()
    val uiStatusLiveData: LiveData<DetailsDataResource> = _uiStatusLiveData

    fun onViewLoaded() {
        val vehicleId = getSelectedVehicleId()
        viewModelScope.launch {
            _uiStatusLiveData.postValue(Loading)
            val response = getRemoteVehicleById(vehicleId)
            _uiStatusLiveData.postValue(VehicleDetailsReceived(response).also {
                if (it.vehicleDetails?.title.isNullOrEmpty()) it.vehicleDetails?.title = NO_TITLE
            })
        }
    }
}