package com.pouyaheydari.android.evehiclerenting.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.pouyaheydari.android.core.domain.VehicleDetails
import com.pouyaheydari.android.evehiclerenting.R
import com.pouyaheydari.android.evehiclerenting.databinding.VehicleDetailsFragmentBinding
import com.pouyaheydari.android.evehiclerenting.presentation.details.DetailsDataResource.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleDetailsFragment : Fragment() {

    private val viewModel: VehicleDetailsViewModel by viewModels()
    private lateinit var binding: VehicleDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VehicleDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewLoaded()

        binding.btnReserve.setOnClickListener {
            viewModel.onReserveRequested()
        }

        viewModel.uiStatusLiveData.observe(viewLifecycleOwner) {
            when (it) {
                DataFetchFailure -> {
                    // TODO
                }
                Loading -> showLoading()
                is VehicleDetailsReceived -> {
                    hideLoading()
                    showVehicleDetails(it.vehicleDetails)
                }
                RentalSuccess -> showRentalSuccess()
            }
        }
    }

    private fun showRentalSuccess() {
        Toast.makeText(
            requireContext(),
            getString(R.string.you_rented_the_vehicle),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showVehicleDetails(vehicleDetails: VehicleDetails?) = with(binding) {
        Glide.with(imgVehicle).load(vehicleDetails?.vehicleTypeImageUrl).into(imgVehicle)
        txtVehicleName.text = vehicleDetails?.title
        txtVehicleLicence.text = vehicleDetails?.licencePlate
        val cleannessImage =
            if (vehicleDetails?.isClean == true) R.drawable.ic_clean_car else R.drawable.ic_dirty_car
        imgCleanness.setImageResource(cleannessImage)
        val damageImage =
            if (vehicleDetails?.isDamaged == true) R.drawable.ic_damaged_car else R.drawable.ic_safe_car
        imgDamage.setImageResource(damageImage)

        txtAddress.text = vehicleDetails?.address
        txtZip.text = vehicleDetails?.zipCode
        txtDamage.text = vehicleDetails?.damageDescription
        txtFuel.text = vehicleDetails?.fuelLevel.toString()
        txtCity.text = vehicleDetails?.city
        txtPricingTime.text = vehicleDetails?.pricingTime
        txtPricingParking.text = vehicleDetails?.pricingParking

    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }
}