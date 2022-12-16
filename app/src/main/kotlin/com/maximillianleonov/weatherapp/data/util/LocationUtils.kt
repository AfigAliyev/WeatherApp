package com.maximillianleonov.weatherapp.data.util

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.maximillianleonov.weatherapp.model.Coord

object LocationUtils {
    @Suppress("MissingPermission")
    fun getCurrentLocation(
        context: Context,
        onSuccess: (Coord) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val client = LocationServices.getFusedLocationProviderClient(context)
        client.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                onFailure(MissingLocationException())
                return@addOnSuccessListener
            }

            val coord = Coord(lat = location.latitude, lon = location.longitude)
            onSuccess(coord)
        }.addOnFailureListener(onFailure)
    }
}

class MissingLocationException : NullPointerException()
