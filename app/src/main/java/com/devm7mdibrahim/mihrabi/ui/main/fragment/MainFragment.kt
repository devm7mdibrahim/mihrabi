package com.devm7mdibrahim.mihrabi.ui.main.fragment

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentMainBinding
import com.devm7mdibrahim.mihrabi.ui.main.viewModel.MainViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var bundle: Bundle
    private lateinit var fragmentMainBinding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val navController: NavController by lazy {
        view?.let { Navigation.findNavController(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        getUserLocation()
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMainBinding.prayerTimesButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_prayerTimesFragment) }
        fragmentMainBinding.azkarButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_azkarFragment) }
        fragmentMainBinding.nearbyMosquesButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_nearMosquesFragment) }
        fragmentMainBinding.quranButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_surahListFragment) }
        fragmentMainBinding.awlMaraAsalyButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_awlMaraAsalyFragment) }
        fragmentMainBinding.imaniatButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_imaniatFragment) }
        fragmentMainBinding.qiblaButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_qiblaFragment) }
        fragmentMainBinding.qadaaButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_qadaaFragment) }
        fragmentMainBinding.fadlElsalahButton.setOnClickListener { navController.navigate(R.id.action_main_fragment_to_fadlElsalahFragment) }
        fragmentMainBinding.misbahaButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_misbahaFragment) }

        fragmentMainBinding.fiqhElsalahButton.setOnClickListener {
            bundle = bundleOf(
                Constants.FIQH_TYPE to 1,
                Constants.FIQH_NAME to fragmentMainBinding.fiqhElsalahButton.text
            )
            navController.navigate(R.id.action_mainFragment_to_fiqhFragment, bundle)
        }
        fragmentMainBinding.fiqhEltaharaButton.setOnClickListener {
            bundle = bundleOf(
                Constants.FIQH_TYPE to 2,
                Constants.FIQH_NAME to fragmentMainBinding.fiqhEltaharaButton.text
            )
            navController.navigate(R.id.action_mainFragment_to_fiqhFragment, bundle)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val mFusedLocationProviderClient = FusedLocationProviderClient(requireContext())
        mFusedLocationProviderClient.lastLocation
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result != null) {
                        updateUserLocation(task.result)
                        updateUserCountry(task.result!!)
                    } else {
                        val locationRequest = LocationRequest.create()
                        locationRequest.apply {
                            interval = 5000
                            fastestInterval = 2000
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
            val geoCoder = Geocoder(requireContext(), Locale("ar"))
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
}
