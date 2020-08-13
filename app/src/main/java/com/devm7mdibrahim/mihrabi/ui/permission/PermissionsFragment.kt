package com.devm7mdibrahim.mihrabi.ui.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentPermissionBinding
import com.devm7mdibrahim.mihrabi.utils.Constants.LOCATION_REQUEST_PERMISSION_CODE


class PermissionsFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var permissionBinding: FragmentPermissionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        permissionBinding = FragmentPermissionBinding.inflate(inflater, container, false)
        return permissionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        permissionBinding.button.setOnClickListener { requestLocationPermissionFromDevice() }
    }

    private fun requestLocationPermissionFromDevice() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                navController.navigate(R.id.action_permissionFragment_to_mainFragment)
            } else {
                Toast.makeText(requireContext(),"من فضلك وافق على سماحية العثور على موقعك لكي تتمكن من استخدام التطبيق",Toast.LENGTH_LONG).show()
            }
        }
    }
}
