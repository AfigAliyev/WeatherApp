package com.maximillianleonov.weatherapp.di

import com.maximillianleonov.weatherapp.BuildConfig
import com.maximillianleonov.weatherapp.data.service.WeatherService
import com.maximillianleonov.weatherapp.data.util.ApiKeyProvider
import com.maximillianleonov.weatherapp.data.util.AuthInterceptor
import com.maximillianleonov.weatherapp.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
object DataModule {
    @Provides
    @Singleton
    fun provideWeatherService(apiKeyProvider: ApiKeyProvider): WeatherService = Retrofit.Builder()
        .baseUrl(Constants.Remote.API_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(apiKeyProvider))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create()

    @Provides
    fun provideApiKeyProvider() = object : ApiKeyProvider {
        override val apiKey: String = BuildConfig.API_KEY
    }
}
