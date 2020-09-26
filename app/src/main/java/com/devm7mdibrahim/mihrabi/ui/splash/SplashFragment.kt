package com.devm7mdibrahim.mihrabi.ui.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.google.firebase.analytics.FirebaseAnalytics

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val navController: NavController by lazy {
        Navigation.findNavController(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnalytic()
        checkLocationPermissionFromDevice()
    }

    private fun checkLocationPermissionFromDevice() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Handler(Looper.getMainLooper()).postDelayed({
                navController.navigate(R.id.action_splashFragment_to_mainFragment)
            }, 1500)
        } else {

            Handler(Looper.getMainLooper()).postDelayed({
                navController.navigate(R.id.action_splashFragment_to_permissionFragment)
            }, 1500)
        }
    }

    private fun initAnalytic() {
        Log.d(TAG, "initAnalytic: ")
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SUCCESS, "initialization successful")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}