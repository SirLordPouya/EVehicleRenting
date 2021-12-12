package com.pouyaheydari.android.evehiclerenting.presentation.map

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pouyaheydari.android.core.domain.Vehicles
import com.pouyaheydari.android.evehiclerenting.R
import com.pouyaheydari.android.evehiclerenting.databinding.FragmentMapsBinding
import com.pouyaheydari.android.evehiclerenting.presentation.map.MapsDataResource.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private val viewModel: MapViewModel by viewModels()
    private var map: GoogleMap? = null
    private val carBitmap by lazy { bitmapDescriptor(R.drawable.ic_car_marker) }

    private fun bitmapDescriptor(@DrawableRes icon: Int): BitmapDescriptor {
        val b = BitmapFactory.decodeResource(resources, icon)
        val smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(smallMarker)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        viewModel.getVehicles()

        googleMap.setOnMarkerClickListener {
            viewModel.onCarClicked(it.tag as Int)
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        viewModel.uiStatusLiveData.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Loading -> {}
                is DataFetchFailure -> {}
                is AllVehiclesReceived -> {
                    showVehiclesOnMap(status.vehicles)
                }
                is CarSelected -> {
                    navigateToCarDetails()
                }
                is CarFocused -> {
                    showFocusedVehicleOnMap(status.selectedCar)
                }
            }
        }
    }

    private fun navigateToCarDetails() {
        findNavController().navigate(R.id.action_mapsFragment_to_vehicleDetailsFragment)
    }

    private fun showFocusedVehicleOnMap(selectedCar: Vehicles?) {
        selectedCar?.let { vehicle ->
            map?.clear()
            val marker = showMarker(vehicle)
            marker?.let {
                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.position, 15F))
                it.showInfoWindow()
            }
        }
    }

    private fun showVehiclesOnMap(vehicles: List<Vehicles>?) {
        map?.clear()
        vehicles?.forEach { showMarker(it) }
    }

    private fun showMarker(vehicles: Vehicles) = with(vehicles) {
        val marker = map?.addMarker(
            MarkerOptions().position(LatLng(lat, lon)).icon(carBitmap).title(title)
        )
        marker?.tag = carId
        marker
    }
}