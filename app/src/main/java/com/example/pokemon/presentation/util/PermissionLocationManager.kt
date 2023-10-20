package com.example.pokemon.presentation.util

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import javax.inject.Inject

class PermissionLocationManager @Inject constructor() {
    fun requestLocationPermission( fragment: Fragment): PermissionResult {
        if( ContextCompat.checkSelfPermission( fragment.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED )
            return PermissionResult.GRANTED
        if( ActivityCompat.shouldShowRequestPermissionRationale( fragment.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION ) )
            return PermissionResult.SHOW_RATIONALE
        return PermissionResult.DENIED
    }
}