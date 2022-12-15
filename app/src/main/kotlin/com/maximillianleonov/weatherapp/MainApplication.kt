package com.maximillianleonov.weatherapp

import android.app.Application
import com.maximillianleonov.weatherapp.di.AppComponent
import com.maximillianleonov.weatherapp.di.DaggerAppComponent
import com.maximillianleonov.weatherapp.util.unsafeLazy

class MainApplication : Application() {
    val appComponent: AppComponent by unsafeLazy { DaggerAppComponent.create() }
}
