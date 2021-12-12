package com.pouyaheydari.android.evehiclerenting.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.pouyaheydari.android.evehiclerenting.databinding.VehicleDetailsFragmentBinding

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

}