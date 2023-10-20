package com.example.pokemon.domain

import android.content.Context
import android.location.Location
import com.example.pokemon.core.util.LocationModule
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationServiceModule: LocationModule
) {

    suspend operator fun invoke(context: Context): Location? {
        return locationServiceModule.getLocation(context)
    }
}