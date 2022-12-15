package com.maximillianleonov.weatherapp.util

import android.content.Context
import com.maximillianleonov.weatherapp.MainApplication
import com.maximillianleonov.weatherapp.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> applicationContext.appComponent
    }
