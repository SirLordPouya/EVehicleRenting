package com.pouyaheydari.android.evehiclerenting.presentation.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pouyaheydari.android.evehiclerenting.R

class VehicleDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleDetailsFragment()
    }

    private lateinit var viewModel: VehicleDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VehicleDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}