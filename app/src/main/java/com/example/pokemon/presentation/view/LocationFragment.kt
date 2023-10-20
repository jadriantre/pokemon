package com.example.pokemon.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.databinding.FragmentLocationBinding
import com.example.pokemon.domain.model.LocationDomain
import com.example.pokemon.presentation.adapter.LocationAdapter
import com.example.pokemon.presentation.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment: Fragment() {

    private val locationViewModel: LocationViewModel by activityViewModels()
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private var isLocationPermissionGranted = 3
    private var locations = mutableListOf<LocationDomain>()
    private var jobLocations: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val view = binding.root
        requestLocationPermission()
        locationViewModel.requestLocationPermission(this)
        binding.rcLocations.layoutManager = LinearLayoutManager(this.requireContext())
        locationViewModel.locationPermission.observe(viewLifecycleOwner, Observer {
            isLocationPermissionGranted = it
            if (isLocationPermissionGranted == 1) {
                launchLocations()
            }
        })
        binding.efabRequestPermission.setOnClickListener {
            requestLocationPermission()
        }
        return view
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if(isGranted) {
            isLocationPermissionGranted = 1
            binding.efabRequestPermission.isVisible = false
            launchLocations()
        }
        else {
            isLocationPermissionGranted = 3
            binding.efabRequestPermission.isVisible = true
        }
    }

    private fun requestLocationPermission() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionGranted = 1
            launchLocations()
            return
        }
        requestPermissionLauncher.launch(permission)
    }

    private fun launchLocations(){
        if( jobLocations != null )
            jobLocations?.cancel()
        locationViewModel.getLocation(this.requireContext())
        jobLocations = viewLifecycleOwner.lifecycleScope.launch {
            locationViewModel.locationFlow?.cancellable()?.collect { it ->
                locations.add(it)
                val adapter = LocationAdapter(locations)
                binding.rcLocations.adapter = adapter
            }
        }
    }

}