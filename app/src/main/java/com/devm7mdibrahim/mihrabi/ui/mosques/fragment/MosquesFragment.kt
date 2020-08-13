package com.devm7mdibrahim.mihrabi.ui.mosques.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.devm7mdibrahim.mihrabi.databinding.FragmentMosquesBinding
import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.Result
import com.devm7mdibrahim.mihrabi.ui.mosques.viewModel.MosquesViewModel
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_mosques.*

@AndroidEntryPoint
class MosquesFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private val mosquesViewModel: MosquesViewModel by viewModels()
    private lateinit var location: String
    private lateinit var longitude: String
    private lateinit var latitude: String
    private lateinit var view: FragmentMosquesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = FragmentMosquesBinding.inflate(inflater, container, false)
        getLocation()
        view.getNearbyMosquesButton.setOnClickListener { getNearByMosques() }
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onMapReady(mMap: GoogleMap) {
        map = mMap
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
    }

    private fun getLocation() {
        location = mosquesViewModel.getLocation()
        val split = location.split(", ")
        latitude = split[0]
        longitude = split[1]
    }


    private fun getNearByMosques() {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude.toDouble(), longitude.toDouble()), 15f))

        mosquesViewModel.fetchNearbyMosques(location)
        mosquesViewModel.nearByMosques.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<List<Result>> -> {
                    it.data.let { mosques ->
                        view.rippleBg.stopRippleAnimation()
                        for (mosque in mosques) {
                            map.addMarker(MarkerOptions().position(LatLng(mosque.geometry.location.lat, mosque.geometry.location.lng)).title(mosque.name))
                        }
                    }
                }

                is DataState.Loading -> {
                    view.rippleBg.startRippleAnimation()
                }

                is DataState.Error -> {
                    view.rippleBg.stopRippleAnimation()
                    Snackbar.make(requireView(), it.exception, Snackbar.LENGTH_LONG).setDuration(5000).show()
                }
            }
        })

    }
}