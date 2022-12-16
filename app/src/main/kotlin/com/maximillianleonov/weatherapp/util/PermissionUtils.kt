package com.maximillianleonov.weatherapp.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    private const val PERMISSION = android.Manifest.permission.ACCESS_COARSE_LOCATION

    fun isPermissionGranted(context: Context) =
        ContextCompat.checkSelfPermission(context, PERMISSION) == PackageManager.PERMISSION_GRANTED

    fun requestPermission(launcher: ActivityResultLauncher<String>) = launcher.launch(PERMISSION)

    fun shouldShowRequestPermissionRationale(activity: Activity) =
        ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSION)
}
