package com.devm7mdibrahim.mihrabi.ui.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devm7mdibrahim.mihrabi.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        checkLocationPermissionFromDevice()
    }

    private fun checkLocationPermissionFromDevice() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Handler().postDelayed({
                navController.navigate(R.id.action_splashFragment_to_mainFragment)
            }, 2000)
        } else {
            Handler().postDelayed({
                navController.navigate(R.id.action_splashFragment_to_permissionFragment)
            }, 2000)
        }
    }
}