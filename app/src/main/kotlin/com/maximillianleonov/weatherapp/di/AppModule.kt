package com.maximillianleonov.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class AppModule(private val context: Context) {
    @Provides
    fun provideApplicationContext() = context
}
