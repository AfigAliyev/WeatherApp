package com.maximillianleonov.weatherapp.ui.permission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.maximillianleonov.weatherapp.R
import com.maximillianleonov.weatherapp.databinding.FragmentPermissionBinding
import com.maximillianleonov.weatherapp.util.PermissionUtils
import com.maximillianleonov.weatherapp.util.animate

class PermissionFragment : Fragment() {
    private var _binding: FragmentPermissionBinding? = null
    private val binding get() = _binding!!

    private val permissionLauncher =
        registerForActivityResult(RequestPermission(), ::handlePermissionResult)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents()
    }

    override fun onResume() {
        super.onResume()
        checkPermissionGranted()
    }

    private fun setupComponents() = with(binding) {
        grantAccessButton.setOnClickListener { PermissionUtils.requestPermission(permissionLauncher) }
        settingsButton.setOnClickListener { openSettings() }
    }

    private fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            navigateToMainScreen()
        } else {
            Snackbar.make(binding.root, R.string.permission_denied, Snackbar.LENGTH_LONG).show()

            if (!PermissionUtils.shouldShowRequestPermissionRationale(requireActivity())) {
                with(binding) {
                    animate()
                    settingsButton.isVisible = true
                }
            }
        }
    }

    private fun checkPermissionGranted() {
        if (PermissionUtils.isPermissionGranted(requireContext())) {
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() =
        findNavController().navigate(R.id.action_permissionFragment_to_mainFragment)

    private fun openSettings() = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", requireContext().packageName, null)
    ).let(::startActivity)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
