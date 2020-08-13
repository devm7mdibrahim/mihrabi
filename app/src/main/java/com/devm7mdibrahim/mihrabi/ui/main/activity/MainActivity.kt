package com.devm7mdibrahim.mihrabi.ui.main.activity

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.ui.main.viewModel.MainViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onStart() {
        super.onStart()
        initAnalytic()
        getUserLocation()
    }


    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val mFusedLocationProviderClient = FusedLocationProviderClient(this)
        mFusedLocationProviderClient.lastLocation
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result != null) {
                        updateUserLocation(task.result)
                        updateUserCountry(task.result!!)
                    } else {
                        val locationRequest = LocationRequest.create()
                        locationRequest.apply {
                            interval = 10000
                            fastestInterval = 5000
                            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        }

                        val locationCallback = object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                super.onLocationResult(locationResult)
                                locationResult.lastLocation.let {
                                    updateUserLocation(it)
                                    updateUserCountry(it)
                                }
                            }
                        }

                        mFusedLocationProviderClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            null
                        )
                    }
                }
            }
    }

    private fun updateUserLocation(location: Location?) {
        location?.run {
            mainViewModel.apply {
                updateUserLatitude(latitude.toString())
                updateUserLongitude(longitude.toString())
                updateUserAltitude(altitude.toString())
            }
        }
    }


    private fun updateUserCountry(location: Location) {
        try {
            val geoCoder = Geocoder(this, Locale("ar"))
            val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                addresses[0].also {
                    mainViewModel.updateUserCity(it.adminArea + " - " + it.countryName)
                }
            }

        } catch (e: Exception) {
            Log.d(Constants.TAG, "getUserCity: " + e.message)
        }
    }

    private fun initAnalytic() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SUCCESS, "initialization successful")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}