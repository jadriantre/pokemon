package com.example.pokemon.core.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationModule @Inject constructor() {
    suspend fun getLocation(@ApplicationContext context: Context): Location?{
        val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER)
        if( !isGPSEnabled )
            return null
        return suspendCancellableCoroutine { cont ->
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED )
            {
                fusedLocationProviderClient.lastLocation.apply {
                    if( isComplete ){
                        if( isSuccessful )
                            cont.resume(result) {}
                        else
                            cont.resume(null) {}
                    }
                    addOnSuccessListener{
                        cont.resume(it) {}
                    }
                    addOnCanceledListener {
                        cont.resume(null) {}
                    }
                    addOnFailureListener {
                        cont.resume(null) {}
                    }
                }
            }else
                cont.resume(null) {}
        }
    }
}