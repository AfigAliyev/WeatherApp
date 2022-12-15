package com.maximillianleonov.weatherapp.util

fun <T> unsafeLazy(initializer: () -> T) =
    lazy(mode = LazyThreadSafetyMode.NONE, initializer = initializer)
