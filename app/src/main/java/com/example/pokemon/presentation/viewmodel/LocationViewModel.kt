package com.example.pokemon.presentation.viewmodel

import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.GetLocationUseCase
import com.example.pokemon.domain.model.LocationDomain
import com.example.pokemon.presentation.util.PermissionLocationManager
import com.example.pokemon.presentation.util.PermissionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class LocationViewModel  @Inject constructor(
    private val permissionLocationManager: PermissionLocationManager,
    private val getLocationUseCase: GetLocationUseCase
): ViewModel() {

    val locationPermission = MutableLiveData<Int>()
    var locationFlow: Flow<LocationDomain>? = null

    fun requestLocationPermission(fragment: Fragment){
        viewModelScope.launch {
            when(permissionLocationManager.requestLocationPermission(fragment)){
                PermissionResult.GRANTED -> { locationPermission.postValue(1) }
                PermissionResult.SHOW_RATIONALE -> { locationPermission.postValue(2) }
                PermissionResult.DENIED -> { locationPermission.postValue(3) }
            }
        }
    }

    fun getLocation(context: Context){
        locationFlow = periodicFlow(120000).flatMapLatest {
            flow{
                val location = getLocationUseCase(context)
                if(location!= null)
                    emit(LocationDomain(location.latitude, location.longitude, getDate() ) )
            }
        }
    }

    private fun periodicFlow(intervalMillis: Long): Flow<Unit> = flow {
        while (true) {
            emit(Unit)
            delay(intervalMillis)
        }
    }

    private fun getDate(): String{
        if (Build.VERSION.SDK_INT >= 26) {
            val pattern = "yyyy-MM-dd HH:mm:ss"
            var now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
            val formatter = DateTimeFormatter.ofPattern(pattern)
            try {
                now = LocalDateTime.parse(now.toString(), formatter)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return now.toString()
        } else {
            val pattern = "yyyy-MM-dd HH:mm:ss"
            val now = Calendar.getInstance()
            lateinit var result: Date
            try {
                result = SimpleDateFormat(pattern).parse(now.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result.toString()
        }
    }

}