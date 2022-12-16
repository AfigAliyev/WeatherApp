package com.maximillianleonov.weatherapp.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.maximillianleonov.weatherapp.MainApplication
import com.maximillianleonov.weatherapp.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> applicationContext.appComponent
    }

val Fragment.appComponent: AppComponent get() = requireContext().appComponent
