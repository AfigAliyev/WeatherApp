package com.maximillianleonov.weatherapp.data.service

import com.maximillianleonov.weatherapp.model.WeatherInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<WeatherInfo>
}
