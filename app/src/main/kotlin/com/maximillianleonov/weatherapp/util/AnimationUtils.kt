package com.maximillianleonov.weatherapp.util

import android.view.ViewGroup
import androidx.transition.TransitionManager
import androidx.viewbinding.ViewBinding

fun ViewBinding.animate() {
    val root = root
    require(root is ViewGroup)
    TransitionManager.beginDelayedTransition(root)
}
