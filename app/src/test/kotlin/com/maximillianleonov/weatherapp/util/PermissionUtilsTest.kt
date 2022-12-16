package com.maximillianleonov.weatherapp.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PermissionUtilsTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var activity: Activity

    @Test
    fun isPermissionGranted_permissionGranted_returnsTrue() {
        val expected = true

        mockkStatic(ContextCompat::class)
        every {
            ContextCompat.checkSelfPermission(activity, PERMISSION)
        } returns PackageManager.PERMISSION_GRANTED

        val actual = PermissionUtils.isPermissionGranted(activity)

        assertEquals(expected, actual)
        verify { ContextCompat.checkSelfPermission(activity, PERMISSION) }
    }

    @Test
    fun isPermissionGranted_permissionDenied_returnsFalse() {
        val expected = false

        mockkStatic(ContextCompat::class)
        every {
            ContextCompat.checkSelfPermission(activity, PERMISSION)
        } returns PackageManager.PERMISSION_DENIED

        val actual = PermissionUtils.isPermissionGranted(activity)

        assertEquals(expected, actual)
        verify { ContextCompat.checkSelfPermission(activity, PERMISSION) }
    }

    @Test
    fun requestPermission_launcher_launchShouldBeCalled() {
        val launcher = mockk<ActivityResultLauncher<String>>(relaxed = true)

        PermissionUtils.requestPermission(launcher)

        verify { launcher.launch(PERMISSION) }
    }

    @Test
    fun shouldShowRequestPermissionRationale_true_returnsTrue() {
        val expected = true

        mockkStatic(ActivityCompat::class)
        every {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSION)
        } returns expected

        val actual = PermissionUtils.shouldShowRequestPermissionRationale(activity)

        assertEquals(expected, actual)
        verify { ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSION) }
    }

    @Test
    fun shouldShowRequestPermissionRationale_false_returnsFalse() {
        val expected = false

        mockkStatic(ActivityCompat::class)
        every {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSION)
        } returns expected

        val actual = PermissionUtils.shouldShowRequestPermissionRationale(activity)

        assertEquals(expected, actual)
        verify { ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSION) }
    }

    private companion object {
        private const val PERMISSION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    }
}
