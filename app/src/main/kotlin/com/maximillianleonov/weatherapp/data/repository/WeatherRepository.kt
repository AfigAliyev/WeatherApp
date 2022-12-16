package com.maximillianleonov.weatherapp.data.repository

import android.content.Context
import com.maximillianleonov.weatherapp.data.service.WeatherService
import com.maximillianleonov.weatherapp.data.util.LocationUtils
import com.maximillianleonov.weatherapp.model.WeatherInfo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val context: Context,
    private val weatherService: WeatherService
) {
    fun getCurrentWeather(): Single<WeatherInfo> = Single.create { emitter ->
        LocationUtils.getCurrentLocation(
            context = context,
            onSuccess = { coord ->
                weatherService.getCurrentWeather(lat = coord.lat, lon = coord.lon)
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(onSuccess = emitter::onSuccess, onError = emitter::onError)
            },
            onFailure = emitter::onError
        )
    }
}
