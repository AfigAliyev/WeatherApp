package com.maximillianleonov.weatherapp.di

import com.maximillianleonov.weatherapp.BuildConfig
import com.maximillianleonov.weatherapp.data.util.ApiKeyProvider
import dagger.Module
import dagger.Provides

@Module
object DataModule {
    @Provides
    fun provideApiKeyProvider() = object : ApiKeyProvider {
        override val apiKey: String = BuildConfig.API_KEY
    }
}
