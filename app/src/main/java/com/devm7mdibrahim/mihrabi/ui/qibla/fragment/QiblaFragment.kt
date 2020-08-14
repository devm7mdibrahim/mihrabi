package com.devm7mdibrahim.mihrabi.ui.qibla.fragment

import android.content.Context
import android.hardware.*
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devm7mdibrahim.mihrabi.databinding.FragmentQiblaBinding
import com.devm7mdibrahim.mihrabi.ui.qibla.viewModel.QiblaViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QiblaFragment : Fragment(), SensorEventListener {

    private val mSensorManager: SensorManager by lazy {
        requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private val orientationSensor: Sensor by lazy {
        mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
    }

    private val qiblaViewModel: QiblaViewModel by viewModels()
    private lateinit var qiblaBinding: FragmentQiblaBinding
    private var direction: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qiblaBinding = FragmentQiblaBinding.inflate(inflater, container, false)
        getDirection()
        return qiblaBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (mSensorManager != null && orientationSensor != null) {
            mSensorManager.registerListener(
                this,
                orientationSensor,
                SensorManager.SENSOR_DELAY_GAME
            )
        } else {
            Toast.makeText(requireContext(), "Sensor not supported", Toast.LENGTH_LONG).show()
        }

        qiblaBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        try {
            val degree = event?.run { values[0] }
            degree?.run {
                RotateAnimation(
                    (-this - 40),
                    direction,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
            }?.apply {
                duration = 210
                fillAfter = true
                qiblaBinding.needle.startAnimation(this)
            }
        } catch (e: Exception) {
            Log.d(TAG, "onSensorChanged: " + e.message)
        }
    }

    private fun getDirection() {
        val userLoc = Location("service Provider")
        userLoc.latitude = qiblaViewModel.getUserLatitude()
        userLoc.longitude = qiblaViewModel.getUserLongitude()
        userLoc.altitude = qiblaViewModel.getUserAltitude()

        val kaabaLoc = Location("service Provider")
        kaabaLoc.altitude = qiblaViewModel.getKaabaLatitude()
        kaabaLoc.longitude = qiblaViewModel.getKaabaLongitude()
        kaabaLoc.altitude = qiblaViewModel.getKaabaAltitude()

        var bearingTo = userLoc.bearingTo(kaabaLoc)
        if (bearingTo < 0) bearingTo += 360

        val geoField = GeomagneticField(
            userLoc.latitude.toFloat(),
            userLoc.longitude.toFloat(),
            userLoc.altitude.toFloat(),
            System.currentTimeMillis()
        )
        val head = -geoField.declination
        direction = bearingTo - head
    }
}
